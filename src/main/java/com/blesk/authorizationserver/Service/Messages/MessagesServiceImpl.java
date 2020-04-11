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
    public Accounts getAccountForVerification(String userName) {
        Accounts accounts = this.amqpTemplate.convertSendAndReceiveAsType("blesk.verifyAccountExchange", "blesk.verifyAccountRoutingKey", userName, new AccountsReference());
        if (accounts == null)
            throw new AuthorizationException(Messages.ACCOUNT_VERIFICATION_ERROR);
        return accounts;
    }

    @Override
    public Accounts sendAccountForRegistration(Accounts accounts) {
        Accounts account = this.amqpTemplate.convertSendAndReceiveAsType("blesk.createAccountExchange", "blesk.createAccountRoutingKey", accounts, new AccountsReference());
        if (accounts == null)
            throw new AuthorizationException(Messages.ACCOUNT_REGISTRATION_ERROR);
        return account;
    }

    @Override
    public Passwords getResetTokenToRecoverAccount(String email) {
        Passwords passwords = this.amqpTemplate.convertSendAndReceiveAsType("blesk.forgetPasswordExchange", "blesk.forgetPasswordRoutingKey", email, new PasswordsReference());
        if (passwords == null)
            throw new AuthorizationException(Messages.ACCOUNT_EMAIL_RECOVERY_ERROR);
        return passwords;
    }

    @Override
    public Boolean sendAccountToCreateNewPassword(Accounts accounts) {
        Boolean result = this.amqpTemplate.convertSendAndReceiveAsType("blesk.changePasswordExchange", "blesk.changePasswordRoutingKey", accounts, new AllowedReference());
        if (result == null)
            throw new AuthorizationException(Messages.RESET_PASSWORD_TOKEN_ERROR);
        return result;
    }

    @Override
    public Boolean sendLoginDetailsToRecord(Logins logins) {
        Boolean result = this.amqpTemplate.convertSendAndReceiveAsType("blesk.lastLoginExchange", "blesk.lastLoginRoutingKey", logins, new AllowedReference());
        if (result == null)
            throw new AuthorizationException(Messages.LOGIN_DETAILS_RECORD_ERROR);
        return result;
    }
}