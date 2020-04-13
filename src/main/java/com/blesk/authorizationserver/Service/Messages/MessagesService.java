package com.blesk.authorizationserver.Service.Messages;

import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Logins;
import com.blesk.authorizationserver.Model.Passwords;

public interface MessagesService {

    Accounts sendAccountForVerification(String userName);

    Boolean sendLoginDetails(Logins logins);

    Accounts sendAccountForRegistration(Accounts accounts);

    Boolean sendActivationTokenToVerify(Accounts accounts);

    Passwords getPasswordTokenToRecoverAccount(String email);

    Boolean sendPasswordTokenToVerify(Accounts accounts);
}