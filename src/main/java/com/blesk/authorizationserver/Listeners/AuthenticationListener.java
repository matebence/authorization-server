package com.blesk.authorizationserver.Listeners;

import com.blesk.authorizationserver.DTO.JwtResponse;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Logins;
import com.blesk.authorizationserver.Service.Accounts.AccountsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationListener {

    @Value("${config.oauth2.max-no-try}")
    private String maxNoTrys;

    private HttpServletRequest httpServletRequest;

    private AccountsServiceImpl accountsService;

    @Autowired
    public AuthenticationListener(AccountsServiceImpl accountsService, HttpServletRequest httpServletRequest) {
        this.accountsService = accountsService;
        this.httpServletRequest = httpServletRequest;
    }

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
        String userName = (String) event.getAuthentication().getPrincipal();
        if (userName != null) {
            Accounts accounts = accountsService.getAccountInformations(userName);
            Logins logins = accounts.getLogin();
            logins.setNoTrys(1 + logins.getNoTrys());
            accounts.setLogin(logins);
            accountsService.updateAccount(accounts);
        }
    }

    @EventListener
    public void authenticationSuccess(AuthenticationSuccessEvent event) {
        if (event.getAuthentication().getPrincipal() instanceof JwtResponse) {
            JwtResponse jwtResponse = (JwtResponse) event.getAuthentication().getPrincipal();
            if (jwtResponse.getAccountId() != null) {
                Accounts accounts = accountsService.getAccount(jwtResponse.getAccountId());
                if (accounts.getLogin().getNoTrys() < Integer.parseInt(maxNoTrys)) {
                    Logins logins = accounts.getLogin();
                    logins.setIpAddress(httpServletRequest.getRemoteAddr());
                    logins.setNoTrys(0);
                    accountsService.updateAccount(accounts);
                }
            }
        }
    }
}