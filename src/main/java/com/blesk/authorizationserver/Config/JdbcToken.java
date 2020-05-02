package com.blesk.authorizationserver.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class JdbcToken extends JdbcTokenStore {

    public JdbcToken(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken oAuth2AccessToken = null;
        try {
            oAuth2AccessToken = super.readAccessToken(tokenValue);
        } catch (EmptyResultDataAccessException ignore) {}
        return oAuth2AccessToken;
    }
}