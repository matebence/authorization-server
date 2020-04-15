package com.blesk.authorizationserver.Controller;

import com.blesk.authorizationserver.DTO.OAuth2.Response;
import com.blesk.authorizationserver.Exception.AuthorizationException;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Activations;
import com.blesk.authorizationserver.Model.Passwords;
import com.blesk.authorizationserver.Service.Messages.MessagesServiceImpl;
import com.blesk.authorizationserver.Value.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.util.HashMap;

@RestController
public class AuthorizationController {

    private TokenStore tokenStore;

    private MessagesServiceImpl messagesService;

    @Autowired
    public AuthorizationController(TokenStore tokenStore, MessagesServiceImpl messagesService) {
        this.tokenStore = tokenStore;
        this.messagesService = messagesService;
    }

    @DeleteMapping(value = "/signout")
    public Response performSignout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String authorization = httpServletRequest.getHeader("Authorization");
        if (authorization == null)
            throw new AuthorizationException(Messages.LOGOUT_EXCEPTION);

        String bearer = authorization.replace("Bearer", "").trim();
        OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(bearer);
        if (accessToken == null)
            throw new AuthorizationException(Messages.LOGOUT_EXCEPTION);

        this.tokenStore.removeAccessToken(accessToken);
        this.tokenStore.removeRefreshToken(accessToken.getRefreshToken());

        Response response = new Response(new Timestamp(System.currentTimeMillis()).toString(), Messages.SIGNOUT_SUCCESS, false);
        response.setNav("signin", ServletUriComponentsBuilder.fromCurrentContextPath().path("signin").toUriString());
        response.setNav("signup", ServletUriComponentsBuilder.fromCurrentContextPath().path("signup").toUriString());
        response.setNav("forgetpassword", ServletUriComponentsBuilder.fromCurrentContextPath().path("forgetpassword").toUriString());

        httpServletResponse.setStatus( HttpServletResponse.SC_OK);
        return response;
    }

    @PostMapping(value = "/signup")
    public Response performSignup(@RequestBody Accounts accounts, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Response response = new Response();
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        Accounts account = this.messagesService.sendAccountForRegistration(accounts);

        if (!account.getValidations().isEmpty()) {
            response.setError(true);
            response.setReason(account.getValidations());
            response.setMessage(Messages.SIGNUP_BAD_DATA);
            httpServletResponse.setStatus( HttpServletResponse.SC_BAD_REQUEST);
            return response;
        }
        if (account.getAccountId() == null)
            throw new AuthorizationException(Messages.SIGNUP_ERROR);

        response.setNav("signin", ServletUriComponentsBuilder.fromCurrentContextPath().path("signin").toUriString());
        response.setNav("forgetpassword", ServletUriComponentsBuilder.fromCurrentContextPath().path("forgetpassword").toUriString());
        response.setMessage(Messages.SIGNUP_SUCCESS);
        response.setError(false);

        httpServletResponse.setStatus( HttpServletResponse.SC_CREATED);
        return response;
    }

    @GetMapping(value = "signup/account/{accountId}/token/{token}")
    public Response performAccountActivationTokenVerification(@PathVariable Long accountId, @PathVariable String token, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Accounts accounts = new Accounts();
        accounts.setAccountId(accountId);
        Activations activations = new Activations();
        activations.setToken(token);
        accounts.setActivations(activations);

        Response response = new Response();
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());

        if (this.messagesService.sendActivationTokenToVerify(accounts)) {
            httpServletResponse.setStatus( HttpServletResponse.SC_OK);
            response.setMessage(Messages.ACTIVATION_TOKEN_SUCCESS);
            response.setError(false);
        } else {
            httpServletResponse.setStatus( HttpServletResponse.SC_BAD_REQUEST);
            response.setMessage(Messages.ACTIVATION_TOKEN_ERROR);
            response.setError(true);
        }

        return response;
    }

    @PostMapping(value = "/forgetpassword")
    public Response performPasswordRecovery(@RequestBody HashMap<String, String> accounts, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (accounts.get("email") == null)
            throw new AuthorizationException(Messages.REQUEST_BODY_NOT_FOUND_EXCEPTION);

        Passwords passwords = this.messagesService.getPasswordTokenToRecoverAccount(accounts.get("email"));
        if (passwords.getAccounts() == null)
            throw new AuthorizationException(Messages.ACCOUNT_EMAIL_RECOVERY_ERROR);

        Response response = new Response(new Timestamp(System.currentTimeMillis()).toString(), Messages.FORGET_PASSWORD_SUCCESS, false);
        response.setNav("signin", ServletUriComponentsBuilder.fromCurrentContextPath().path("signin").toUriString());
        response.setNav("signup", ServletUriComponentsBuilder.fromCurrentContextPath().path("signup").toUriString());

        httpServletResponse.setStatus( HttpServletResponse.SC_CREATED);
        return response;
    }

    @GetMapping(value = "/forgetpassword/account/{accountId}/token/{token}")
    @ResponseStatus(HttpStatus.OK)
    public Response performPasswordResetTokenVerification(@PathVariable Long accountId, @PathVariable String token, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Accounts accounts = new Accounts();
        accounts.setAccountId(accountId);
        Passwords passwords = new Passwords();
        passwords.setToken(token);
        accounts.setPasswords(passwords);

        Response response = new Response();
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());

        if (this.messagesService.sendPasswordTokenToVerify(accounts)) {
            httpServletResponse.setStatus( HttpServletResponse.SC_OK);
            response.setMessage(Messages.PASSWORD_TOKEN_SUCCESS);
            response.setError(false);
        } else {
            httpServletResponse.setStatus( HttpServletResponse.SC_BAD_REQUEST);
            response.setMessage(Messages.PASSWORD_TOKEN_ERROR);
            response.setError(true);
        }

        return response;
    }
}