package com.blesk.authorizationserver.Config;

import com.blesk.authorizationserver.Value.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

public class JdbcToken extends JdbcTokenStore {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    public JdbcToken(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = null;

        try {
            accessToken = new DefaultOAuth2AccessToken(tokenValue);
        } catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info(Messages.ACCESS_TOKEN_ERROR + tokenValue);
            }
        } catch (IllegalArgumentException e) {
            LOG.warn(Messages.ACCESS_TOKEN_FAILED + tokenValue, e);
            removeAccessToken(tokenValue);
        }

        return accessToken;
    }
}