package top.wayne06.generalbackend.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

import static top.wayne06.generalbackend.constant.CommonConstant.*;

/**
 * Network utils
 *
 * @author wayne
 */
public class NetUtils {

    /**
     * get client IP address
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader(X_FORWARDED_FOR);
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals(LOCALHOST_IP)) {
                // get ip based on the network card
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (inetAddress != null) {
                    ip = inetAddress.getHostAddress();
                }
            }
        }
        // In the case of multiple proxy, the 1st ip is the real ip of the client. Multiple ips are divided to ','
        if (ip != null && ip.length() > FIFTEEN) {
            if (ip.indexOf(COMMA) > 0) {
                ip = ip.substring(0, ip.indexOf(COMMA));
            }
        }
        if (ip == null) {
            return LOCALHOST_IP;
        }
        return ip;
    }

}
