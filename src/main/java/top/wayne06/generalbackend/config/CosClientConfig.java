package top.wayne06.generalbackend.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cloud Object Storage client
 *
 * @author https://github.com/wayne06
 */
@Configuration
@ConfigurationProperties(prefix = "cos.client")
@Data
public class CosClientConfig {

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * region
     */
    private String region;

    /**
     * bucket
     */
    private String bucket;

    @Bean
    public COSClient cosClient() {
        // initialize user credentials (accessKey, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // set region of bucket, COS region abbreviation refers to https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // generate COS client
        return new COSClient(cred, clientConfig);
    }
}
