package com.blesk.authorizationserver.Config;

import com.blesk.authorizationserver.DTO.JwtResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class JwtConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        JwtResponse user = (JwtResponse) authentication.getPrincipal();
        Map<String, Object> data = new LinkedHashMap<>(accessToken.getAdditionalInformation());

        if (user.getAccountId() != null)
            data.put("account_id", user.getAccountId());
        if (user.getUserName() != null)
            data.put("user_name", user.getUserName());
        if (user.getBalance() != null)
            data.put("balance", user.getBalance());
        if (user.isActivated() != null)
            data.put("activated", user.isActivated());

        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        defaultOAuth2AccessToken.setAdditionalInformation(data);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }
}