package com.blesk.authorizationserver.Controller;

import com.blesk.authorizationserver.DTO.OAuth2.Response;
import com.blesk.authorizationserver.Exception.AuthorizationException;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Activations;
import com.blesk.authorizationserver.Model.Passwords;
import com.blesk.authorizationserver.Service.Emails.EmailsServiceImpl;
import com.blesk.authorizationserver.Service.Messages.MessagesServiceImpl;
import com.blesk.authorizationserver.Value.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthorizationController {

    @Value("${blesk.javamailer.url.forget-password}")
    private String resetPasswordUrl;

    @Value("${blesk.javamailer.url.account-activation}")
    private String activationUrl;

    private TokenStore tokenStore;

    private MessagesServiceImpl messagesService;

    private EmailsServiceImpl emailsService;

    @Autowired
    public AuthorizationController(TokenStore tokenStore, MessagesServiceImpl messagesService, EmailsServiceImpl emailsService) {
        this.tokenStore = tokenStore;
        this.messagesService = messagesService;
        this.emailsService = emailsService;
    }

    @DeleteMapping(value = "/signout")
    @ResponseStatus(HttpStatus.OK)
    public Response performSignout(HttpServletRequest httpServletRequest) {
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
        return response;
    }

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Response performSignup(@RequestBody Accounts accounts) {
        Response response = new Response();
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        Accounts account = this.messagesService.sendAccountForRegistration(accounts);

        if (!account.getValidations().isEmpty()) {
            response.setError(false);
            response.setReason(account.getValidations());
            response.setMessage(Messages.SIGNUP_BAD_DATA);
            return response;
        }
        if (account.getAccountId() == null)
            throw new AuthorizationException(Messages.SIGNUP_ERROR);

        Map<String, Object> variables = new HashMap<>();
        variables.put("activationUrl", String.format(this.activationUrl, account.getAccountId(), account.getActivations().getToken()));
        this.emailsService.sendHtmlMesseage("Registrácia", "signupactivation", variables, account);

        response.setNav("signin", ServletUriComponentsBuilder.fromCurrentContextPath().path("signin").toUriString());
        response.setNav("forgetpassword", ServletUriComponentsBuilder.fromCurrentContextPath().path("forgetpassword").toUriString());
        response.setMessage(Messages.SIGNUP_SUCCESS);
        response.setError(false);
        return response;
    }

    @GetMapping(value = "signup/account/{accountId}/token/{token}")
    @ResponseStatus(HttpStatus.OK)
    public Response performAccountActivationTokenVerification(@PathVariable Long accountId, @PathVariable String token) {
        Accounts accounts = new Accounts();
        accounts.setAccountId(accountId);
        Activations activations = new Activations();
        activations.setToken(token);
        accounts.setActivations(activations);

        Response response = new Response();
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());

        if (this.messagesService.sendPasswordTokenToVerify(accounts)) {
            response.setMessage(Messages.ACTIVATION_TOKEN_SUCCESS);
            response.setError(true);
        } else {
            response.setMessage(Messages.ACTIVATION_TOKEN_ERROR);
            response.setError(true);
        }

        return response;
    }

    @PostMapping(value = "/forgetpassword")
    @ResponseStatus(HttpStatus.OK)
    public Response performPasswordRecovery(@RequestBody HashMap<String, String> accounts) {
        if (accounts.get("email") == null)
            throw new AuthorizationException(Messages.REQUEST_BODY_NOT_FOUND_EXCEPTION);

        Passwords passwords = this.messagesService.getPasswordTokenToRecoverAccount(accounts.get("email"));
        if (passwords.getAccount() == null)
            throw new AuthorizationException(Messages.ACCOUNT_EMAIL_RECOVERY_ERROR);

        Map<String, Object> variables = new HashMap<>();
        variables.put("resetPasswordUrl", String.format(this.resetPasswordUrl, passwords.getAccount().getAccountId(), passwords.getToken()));
        this.emailsService.sendHtmlMesseage("Zabudnuté heslo", "forgetpassword", variables, passwords.getAccount());

        Response response = new Response(new Timestamp(System.currentTimeMillis()).toString(), Messages.FORGET_PASSWORD_SUCCESS, false);
        response.setNav("signin", ServletUriComponentsBuilder.fromCurrentContextPath().path("signin").toUriString());
        response.setNav("signup", ServletUriComponentsBuilder.fromCurrentContextPath().path("signup").toUriString());
        return response;
    }

    @GetMapping(value = "/forgetpassword/account/{accountId}/token/{token}")
    @ResponseStatus(HttpStatus.OK)
    public Response performPasswordResetTokenVerification(@PathVariable Long accountId, @PathVariable String token) {
        Accounts accounts = new Accounts();
        accounts.setAccountId(accountId);
        Passwords passwords = new Passwords();
        passwords.setToken(token);
        accounts.setPasswords(passwords);

        Response response = new Response();
        response.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());

        if (this.messagesService.sendPasswordTokenToVerify(accounts)) {
            response.setMessage(Messages.PASSWORD_TOKEN_SUCCESS);
            response.setError(false);
        } else {
            response.setMessage(Messages.PASSWORD_TOKEN_ERROR);
            response.setError(true);
        }

        return response;
    }
}