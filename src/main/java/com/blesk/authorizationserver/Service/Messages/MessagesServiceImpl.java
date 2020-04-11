package com.blesk.authorizationserver.Service.Message;

import com.blesk.accountservice.DTO.ResponseMessage;
import com.blesk.accountservice.Model.Accounts;
import com.blesk.accountservice.Value.Messages;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MessagesServiceImpl implements MessagesService {

    @Value("${blesk.rabbitmq.authorization-exchange}")
    private String authorizationExchange;

    @Value("${blesk.rabbitmq.authorization-routing-key}")
    private String authorizationRoutingkey;

    private AmqpTemplate amqpTemplate;

    @Autowired
    public MessagesServiceImpl(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public ResponseMessage send(Accounts accounts, String  message, boolean status) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());

        try {
            this.amqpTemplate.convertAndSend(this.authorizationExchange, this.authorizationRoutingkey, accounts);
            responseMessage.setMessage(message);
            responseMessage.setError(status);
            return responseMessage;
        } catch (AmqpException ex) {
            responseMessage.setMessage(Messages.EXCEPTION);
            responseMessage.setError(true);
            return responseMessage;
        }
    }
}