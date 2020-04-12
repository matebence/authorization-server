package com.blesk.authorizationserver.Utilities;

import javax.servlet.http.HttpServletRequest;

public class Tools {

    public Tools() {
    }

    public static String getClientIP(HttpServletRequest httpServletRequest) {
        String xfHeader = httpServletRequest.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return httpServletRequest.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}