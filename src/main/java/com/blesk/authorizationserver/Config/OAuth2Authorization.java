package com.blesk.authorizationserver.Config;

import com.blesk.authorizationserver.Exceptions.Handler.OAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
public class OAuth2 extends AuthorizationServerConfigurerAdapter {

    @Value("${config.oauth2.client-id}")
    private String clientId;

    @Value("${config.oauth2.client-secret}")
    private String clientSecret;

    @Value("${config.oauth2.access-token-validity}")
    private String accessTokenValidity;

    @Value("${config.oauth2.refresh-token-validity}")
    private String refreshTokenValidity;

    @Value("${config.oauth2.private-key}")
    private String privateKey;

    @Value("${config.oauth2.public-key}")
    private String publicKey;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private DataSource dataSource;

    @Autowired
    public OAuth2(PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).withClient(this.clientId).secret(this.passwordEncoder.encode(this.clientSecret))
                .scopes("trust")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(Integer.parseInt(this.accessTokenValidity))
                .refreshTokenValiditySeconds(Integer.parseInt(this.refreshTokenValidity));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(this.authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer()).pathMapping("/oauth/token", "/signin").exceptionTranslator(new OAuthHandler());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtConverter();
        converter.setSigningKey(this.privateKey);
        converter.setVerifierKey(this.publicKey);
        return converter;
    }
}