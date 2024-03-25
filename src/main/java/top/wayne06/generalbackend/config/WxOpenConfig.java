package top.wayne06.generalbackend.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Weixin open platform configuration
 *
 * @author https://github.com/wayne06
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "wx.open")
@Data
public class WxOpenConfig {

    /**
     * appId
     */
    private String appId;

    /**
     * appSecret
     */
    private String appSecret;

    /**
     * wxMpService
     */
    private WxMpService wxMpService;

    /**
     * singleton (did not use @Bean is to prevent the conflict with the service of Official Account Platform)
     *
     * @return
     */
    public WxMpService getWxMpService() {
        if (wxMpService != null) {
            return wxMpService;
        }
        synchronized (this) {
            if (wxMpService != null) {
                return wxMpService;
            }
            WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
            config.setAppId(appId);
            config.setSecret(appSecret);
            WxMpService service = new WxMpServiceImpl();
            service.setWxMpConfigStorage(config);
            wxMpService = service;
            return wxMpService;
        }
    }
}
