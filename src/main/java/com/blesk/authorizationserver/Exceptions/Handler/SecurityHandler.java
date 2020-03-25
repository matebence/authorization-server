package com.blesk.authorizationserver.Exceptions.Handler;

import com.blesk.authorizationserver.Values.Messages;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class SecurityHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(HttpStatus.FORBIDDEN.value());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", true);
        jsonObject.put("message", Messages.AUTH_REQUIRED_EXCEPTION);
        jsonObject.put("timestamp", new Timestamp(System.currentTimeMillis()).toString());
        res.getWriter().write(jsonObject.toJSONString());
    }
}