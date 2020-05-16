package com.blesk.authorizationserver.Config;

import com.blesk.authorizationserver.DTO.OAuth2.Account;
import com.blesk.authorizationserver.DTO.OAuth2.Jwt;
import com.blesk.authorizationserver.Translator.OAuthTranslator;
import com.blesk.authorizationserver.Service.Attempts.AttemptsServiceImpl;
import com.blesk.authorizationserver.Utilitie.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@Configuration
public class OAuth2Authorization extends AuthorizationServerConfigurerAdapter {

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

    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    private AttemptsServiceImpl attemptServiceImpl;

    private HttpServletRequest httpServletRequest;

    private PasswordEncoder passwordEncoder;

    private DataSource dataSource;

    @Autowired
    public OAuth2Authorization(AuthenticationManager authenticationManager, AttemptsServiceImpl attemptServiceImpl, HttpServletRequest httpServletRequest, PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.attemptServiceImpl = attemptServiceImpl;
        this.httpServletRequest = httpServletRequest;
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(this.dataSource).withClient(this.clientId).secret(this.passwordEncoder.encode(this.clientSecret))
                .scopes("trust")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(Integer.parseInt(this.accessTokenValidity))
                .refreshTokenValiditySeconds(Integer.parseInt(this.refreshTokenValidity));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(this.authenticationManager).tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer()).pathMapping("/oauth/token", "/signin").exceptionTranslator(new OAuthTranslator());
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
        return new JdbcToken(this.dataSource);
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new Jwt();
        converter.setSigningKey(this.privateKey);
        converter.setVerifierKey(this.publicKey);
        return converter;
    }

    @EventListener
    public void authSuccessEventListener(AuthenticationSuccessEvent authorizedEvent) {
        if (authorizedEvent.getAuthentication().getPrincipal() instanceof Account) {
            Account account = (Account) authorizedEvent.getAuthentication().getPrincipal();
            this.attemptServiceImpl.loginSucceeded(Tools.getClientIP(this.httpServletRequest), account);
        }
    }

    @EventListener
    public void authFailedEventListener(AbstractAuthenticationFailureEvent oAuth2AuthenticationFailureEvent) {
        this.attemptServiceImpl.loginFailed(Tools.getClientIP(this.httpServletRequest));
    }
}