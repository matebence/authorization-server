package com.blesk.authorizationserver.DTO.OAuth2;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Jwt extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Account account = (Account) authentication.getPrincipal();
        Map<String, Object> data = new LinkedHashMap<>(accessToken.getAdditionalInformation());

        if (account.getAccountId() != null)
            data.put("account_id", account.getAccountId());
        if (account.getAccountId() != null)
            data.put("login_id", account.getLoginId());
        if (account.getUserName() != null)
            data.put("user_name", account.getUserName());
        if (account.isActivated() != null)
            data.put("activated", account.isActivated());

        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        defaultOAuth2AccessToken.setAdditionalInformation(data);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }
}