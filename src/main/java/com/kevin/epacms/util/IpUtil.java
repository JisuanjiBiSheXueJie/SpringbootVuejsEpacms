package com.kevin.epacms.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kevin
 * @since 2022/10/22 16:52
 */
public class IpUtil {
    private static final String UNKNOWN = "unknown";

    /**
     * 获取用户IP地址
     *
     * @param request 请求
     * @return {@link String}
     */
    public static String getIPAddress(HttpServletRequest request) {

        String ip = null;
        if (ObjectUtil.isEmpty(request)){
            return StrUtil.EMPTY;
        }

        //X-Forwarded-For：Squid 服务代理
        String ipAddress = request.getHeader("X-Forwarded-For");

        if (isaBoolean(ipAddress)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (isaBoolean(ipAddress)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (isaBoolean(ipAddress)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }

        if (isaBoolean(ipAddress)) {
            //X-Real-IP：nginx服务代理
            ipAddress = request.getHeader("X-Real-IP");
        }

        // 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (StrUtil.isNotBlank(ipAddress)) {
            ip = ipAddress.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (StrUtil.isBlank(ip) || StrUtil.equalsIgnoreCase(UNKNOWN, ipAddress)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static boolean isaBoolean(String ipAddress) {
        return StrUtil.isBlank(ipAddress) || StrUtil.equalsIgnoreCase(UNKNOWN, ipAddress);
    }
}
