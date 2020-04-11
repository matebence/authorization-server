package com.blesk.authorizationserver.Service;

import com.blesk.authorizationserver.DTO.OAuth2.Jwt;
import com.blesk.authorizationserver.Exception.AuthorizationException;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Model.Roles;
import com.blesk.authorizationserver.Value.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class OAuth2DetailServiceImpl implements OAuth2DetailService {

    private LoginAttemptService loginAttemptService;

    private HttpServletRequest request;

    @Autowired
    public OAuth2DetailServiceImpl(LoginAttemptService loginAttemptService, HttpServletRequest request) {
        this.loginAttemptService = loginAttemptService;
        this.request = request;
    }

    @Override
    public String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new AuthorizationException(Messages.BLOCKED_EXCEPTION);
        }

        //Reaplce with API CALL
        // Accounts accounts = this.getAccountInformations(userName);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : accounts.getRoles()) {
            for (Privileges privilege : role.getPrivileges()) {
                authorities.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        accounts.setGrantedAuthorities(authorities);

        return new Jwt(accounts);
    }
}