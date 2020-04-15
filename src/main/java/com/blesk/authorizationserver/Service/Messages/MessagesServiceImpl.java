package com.blesk.authorizationserver.Service.Messages;

import com.blesk.authorizationserver.DTO.RabbitMQ.AccountsReference;
import com.blesk.authorizationserver.DTO.RabbitMQ.AllowedReference;
import com.blesk.authorizationserver.DTO.RabbitMQ.PasswordsReference;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Logins;
import com.blesk.authorizationserver.Model.Passwords;
import com.blesk.authorizationserver.Exception.AuthorizationException;
import com.blesk.authorizationserver.Value.Messages;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagesServiceImpl implements MessagesService {

    private AmqpTemplate amqpTemplate;

    @Autowired
    public MessagesServiceImpl(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public Accounts sendAccountForVerification(String userName) {
        Accounts accounts = this.amqpTemplate.convertSendAndReceiveAsType("blesk.verifyAccountExchange", "blesk.verifyAccountRoutingKey", userName, new AccountsReference());
        if (accounts == null)
            throw new AuthorizationException(Messages.SERVER_ERROR);
        return accounts;
    }

    @Override
    public Boolean sendLoginDetails(Logins logins) {
        Boolean result = this.amqpTemplate.convertSendAndReceiveAsType("blesk.lastLoginExchange", "blesk.lastLoginRoutingKey", logins, new AllowedReference());
        if (result == null)
            throw new AuthorizationException(Messages.SERVER_ERROR);
        if (result == Boolean.FALSE)
            throw new AuthorizationException(Messages.LOGIN_DETAILS_ERROR);
        return result;
    }

    @Override
    public Accounts sendAccountForRegistration(Accounts accounts) {
        Accounts account = this.amqpTemplate.convertSendAndReceiveAsType("blesk.createAccountExchange", "blesk.createAccountRoutingKey", accounts, new AccountsReference());
        if (account == null)
            throw new AuthorizationException(Messages.SERVER_ERROR);
        return account;
    }

    @Override
    public Boolean sendActivationTokenToVerify(Accounts accounts) {
        Boolean result = this.amqpTemplate.convertSendAndReceiveAsType("blesk.verifyActivationTokenExchange", "blesk.verifyActivationTokenRoutingKey", accounts, new AllowedReference());
        if (result == null)
            throw new AuthorizationException(Messages.SERVER_ERROR);
        if (result == Boolean.FALSE)
            throw new AuthorizationException(Messages.ACTIVATION_TOKEN_VERIFY_ERROR);
        return result;
    }

    @Override
    public Passwords getPasswordTokenToRecoverAccount(String email) {
        Passwords passwords = this.amqpTemplate.convertSendAndReceiveAsType("blesk.forgetPasswordExchange", "blesk.forgetPasswordRoutingKey", email, new PasswordsReference());
        if (passwords == null)
            throw new AuthorizationException(Messages.SERVER_ERROR);
        return passwords;
    }

    @Override
    public Boolean sendPasswordTokenToVerify(Accounts accounts) {
        Boolean result = this.amqpTemplate.convertSendAndReceiveAsType("blesk.verifyPasswordTokenExchange", "blesk.verifyPasswordTokenRoutingKey", accounts, new AllowedReference());
        if (result == null)
            throw new AuthorizationException(Messages.SERVER_ERROR);
        if (result == Boolean.FALSE)
            throw new AuthorizationException(Messages.PASSWORD_TOKEN_VERIFY_ERROR);
        return result;
    }
}