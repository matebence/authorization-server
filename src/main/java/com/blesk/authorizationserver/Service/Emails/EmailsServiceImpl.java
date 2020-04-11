package com.blesk.authorizationserver.Service.Email;

import com.blesk.accountservice.Model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailsServiceImpl implements EmailsService {

    @Value("${blesk.javamailer.from}")
    private String from;

    private JavaMailSender emailSender;

    @Autowired
    public EmailsServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendMessageToAccount(String subject, String message, Accounts accounts) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(accounts.getEmail());
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);

            this.emailSender.send(simpleMailMessage);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}