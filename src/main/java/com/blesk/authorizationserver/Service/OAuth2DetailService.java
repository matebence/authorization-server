package com.blesk.authorizationserver.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface OAuth2DetailService extends UserDetailsService {

    String getClientIP();
}