package com.blesk.authorizationserver.Config;

import com.blesk.authorizationserver.DTO.OAuth2.Jwt;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class JwtConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        Map<String, Object> data = new LinkedHashMap<>(accessToken.getAdditionalInformation());

        if (jwt.getAccountId() != null)
            data.put("account_id", jwt.getAccountId());
        if (jwt.getUserName() != null)
            data.put("user_name", jwt.getUserName());
        if (jwt.isActivated() != null)
            data.put("activated", jwt.isActivated());

        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        defaultOAuth2AccessToken.setAdditionalInformation(data);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }
}