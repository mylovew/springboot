package com.zwk.springboot.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboot
 * @description:
 * @author: wkzhang
 * @create: 2019-08-08 10:34
 */
public class WebUtilsPro {
    /**
     * 判断是否为ajax
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
