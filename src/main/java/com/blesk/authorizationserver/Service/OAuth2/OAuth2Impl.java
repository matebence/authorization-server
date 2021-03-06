package com.blesk.authorizationserver.Service.OAuth2;

import com.blesk.authorizationserver.DTO.OAuth2.Account;
import com.blesk.authorizationserver.Model.AccountRoles;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Exception.AuthorizationException;
import com.blesk.authorizationserver.Service.Attempts.AttemptsServiceImpl;
import com.blesk.authorizationserver.Service.Messages.MessagesServiceImpl;
import com.blesk.authorizationserver.Utilitie.Tools;
import com.blesk.authorizationserver.Value.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class OAuth2Impl implements OAuth2 {

    @Value("${config.oauth2.block-account}")
    private Integer blockAccount;

    private AttemptsServiceImpl attemptServiceImpl;

    private HttpServletRequest httpServletRequest;

    private MessagesServiceImpl messagesService;

    @Autowired
    public OAuth2Impl(AttemptsServiceImpl attemptServiceImpl, HttpServletRequest httpServletRequest, MessagesServiceImpl messagesService) {
        this.attemptServiceImpl = attemptServiceImpl;
        this.httpServletRequest = httpServletRequest;
        this.messagesService = messagesService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        if (this.attemptServiceImpl.isBlocked(Tools.getClientIP(this.httpServletRequest)))
            throw new AuthorizationException(String.format(Messages.BLOCKED_EXCEPTION, blockAccount));

        Accounts accounts = this.messagesService.sendAccountForVerification(userName);

        if (accounts.getAccountId() == null) throw new AuthorizationException(Messages.ACCOUNT_VERIFICATION_ERROR);
        if (!accounts.getActivated()) throw new AuthorizationException(Messages.NOT_ACTIVATED_EXCEPTION);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (AccountRoles accountRoles : accounts.getAccountRoles()) {
            authorities.add(new SimpleGrantedAuthority(accountRoles.getRoles().getName()));
        }
        accounts.setGrantedAuthorities(authorities);

        return new Account(accounts);
    }
}