package com.blesk.authorizationserver.Service.OAuth2;

import com.blesk.authorizationserver.DTO.OAuth2.Account;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Model.Roles;
import com.blesk.authorizationserver.Exception.AuthorizationException;
import com.blesk.authorizationserver.Service.Attempts.AttemptsServiceImpl;
import com.blesk.authorizationserver.Service.Messages.MessagesServiceImpl;
import com.blesk.authorizationserver.Utilities.Tools;
import com.blesk.authorizationserver.Value.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class OAuth2Impl implements OAuth2 {

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
    public UserDetails loadUserByUsername(String userName){
        if (this.attemptServiceImpl.isBlocked(Tools.getClientIP(this.httpServletRequest))) {
            throw new AuthorizationException(Messages.BLOCKED_EXCEPTION);
        }

        Accounts accounts = this.messagesService.getAccountForVerification(userName);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : accounts.getRoles()) {
            for (Privileges privilege : role.getPrivileges()) {
                authorities.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        accounts.setGrantedAuthorities(authorities);

        return new Account(accounts);
    }
}