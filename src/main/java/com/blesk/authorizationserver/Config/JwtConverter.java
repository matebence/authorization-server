package com.blesk.authorizationserver.DTO;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class JwtAccessToken extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        JwtAcoounts user = (JwtAcoounts) authentication.getPrincipal();
        Map<String, Object> data = new LinkedHashMap<>(accessToken.getAdditionalInformation());

        if (user.getAccountId() != null)
            data.put("accountId", user.getAccountId());
        if (user.getUserName() != null)
            data.put("userName", user.getUserName());
        if (user.getBalance() != null)
            data.put("balance", user.getBalance());
        if (user.isActivated() != null)
            data.put("activated", user.isActivated());

        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        defaultOAuth2AccessToken.setAdditionalInformation(data);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }
}