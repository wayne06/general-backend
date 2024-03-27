package top.wayne06.generalbackend.controller;

import top.wayne06.generalbackend.wxmp.WxMpConstant;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts.MenuButtonType;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static top.wayne06.generalbackend.constant.CommonConstant.*;

/**
 * Weixin official account controller
 *
 * @author wayne06
 **/
@RestController
@RequestMapping("/")
@Slf4j
public class WxMpController {

    @Resource
    private WxMpService wxMpService;

    @Resource
    private WxMpMessageRouter router;

    @PostMapping("/")
    public void receiveMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        // check message signature to determine if it is a message sent by a public platform
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            response.getWriter().println("Illegal request.");
        }
        // encrypt type
        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw"
                : request.getParameter("encrypt_type");
        // raw: plaintext
        if (RAW.equals(encryptType)) {
            return;
        }
        // aes: encrypted message
        if (AES.equals(encryptType)) {
            // decrypting message
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(),
                    wxMpService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            log.info("message content = {}", inMessage.getContent());
            // routing message and processing
            WxMpXmlOutMessage outMessage = router.route(inMessage);
            if (outMessage == null) {
                response.getWriter().write("");
            } else {
                response.getWriter().write(outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage()));
            }
            return;
        }
        response.getWriter().println("Unrecognized encryption type.");
    }

    @GetMapping("/")
    public String check(String timestamp, String nonce, String signature, String echoStr) {
        log.info("check");
        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echoStr;
        } else {
            return "";
        }
    }

    /**
     * set menu of official account
     *
     * @return
     * @throws WxErrorException
     */
    @GetMapping("/setMenu")
    public String setMenu() throws WxErrorException {
        log.info("setMenu");
        WxMenu wxMenu = new WxMenu();
        // menu1
        WxMenuButton wxMenuButton1 = new WxMenuButton();
        wxMenuButton1.setType(MenuButtonType.VIEW);
        wxMenuButton1.setName("MainMenu1");
        // submenu
        WxMenuButton wxMenuButton1SubButton1 = new WxMenuButton();
        wxMenuButton1SubButton1.setType(MenuButtonType.VIEW);
        wxMenuButton1SubButton1.setName("JumpTo");
        wxMenuButton1SubButton1.setUrl("http://wayne06.top");
        wxMenuButton1.setSubButtons(Collections.singletonList(wxMenuButton1SubButton1));

        // menu2
        WxMenuButton wxMenuButton2 = new WxMenuButton();
        wxMenuButton2.setType(MenuButtonType.CLICK);
        wxMenuButton2.setName("Click");
        wxMenuButton2.setKey(WxMpConstant.CLICK_MENU_KEY);

        // menu3
        WxMenuButton wxMenuButton3 = new WxMenuButton();
        wxMenuButton3.setType(MenuButtonType.VIEW);
        wxMenuButton3.setName("MainMenu3");
        WxMenuButton wxMenuButton3SubButton1 = new WxMenuButton();
        wxMenuButton3SubButton1.setType(MenuButtonType.VIEW);
        wxMenuButton3SubButton1.setName("Something fun.");
        wxMenuButton3SubButton1.setUrl("http://wayne06.top");
        wxMenuButton3.setSubButtons(Collections.singletonList(wxMenuButton3SubButton1));

        // set main menu
        wxMenu.setButtons(Arrays.asList(wxMenuButton1, wxMenuButton2, wxMenuButton3));
        wxMpService.getMenuService().menuCreate(wxMenu);
        return "ok";
    }
}
