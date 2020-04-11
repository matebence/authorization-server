package com.blesk.authorizationserver.Service.Messages;

import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Logins;
import com.blesk.authorizationserver.Model.Passwords;

public interface MessagesService {

    Accounts getAccountForVerification(String userName);

    Accounts sendAccountForRegistration(Accounts accounts);

    Passwords getResetTokenToRecoverAccount(String email);

    Boolean sendAccountToCreateNewPassword(Accounts accounts);

    Boolean sendLoginDetailsToRecord(Logins logins);
}