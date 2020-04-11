package com.blesk.authorizationserver.Service.Email;

import com.blesk.accountservice.Model.Accounts;

public interface EmailsService {

    void sendMessageToAccount(String subject, String text, Accounts accounts);
}