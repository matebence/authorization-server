package com.blesk.authorizationserver.Service.Message;

import com.blesk.accountservice.DTO.ResponseMessage;
import com.blesk.accountservice.Model.Accounts;

public interface MessagesService {

    ResponseMessage send(Accounts accounts, String message, boolean status);
}