package com.blesk.authorizationserver.Controller;

import com.blesk.authorizationserver.Exception.AuthorizationException;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Value.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class OAuth2Controller {

    private TokenStore tokenStore;

    @Autowired
    public OAuth2Controller(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @DeleteMapping(value = "/signout")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> performSignout(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        if (authorization == null)
            throw new AuthorizationException(Messages.LOGOUT_EXCEPTION);

        String bearer = authorization.replace("Bearer", "").trim();
        OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(bearer);
        this.tokenStore.removeAccessToken(accessToken);
        this.tokenStore.removeRefreshToken(accessToken.getRefreshToken());

        HashMap<String, String> navigation = new HashMap<>();
        navigation.put("signin", ServletUriComponentsBuilder.fromCurrentContextPath().path("signin").toUriString());
        navigation.put("signup", ServletUriComponentsBuilder.fromCurrentContextPath().path("signup").toUriString());
        navigation.put("forgetpassword", ServletUriComponentsBuilder.fromCurrentContextPath().path("forgetpassword").toUriString());

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri()).body(new ResponseMessage(navigation));
    }

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void performSignup(@Valid @RequestBody Accounts accounts) {
        //calling rest api
    }
}