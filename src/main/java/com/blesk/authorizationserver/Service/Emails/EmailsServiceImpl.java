package com.blesk.authorizationserver.Service.Emails;

import com.blesk.authorizationserver.Component.HtmlMailer;
import com.blesk.authorizationserver.Model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class EmailsServiceImpl implements EmailsService {

    @Value("${blesk.javamailer.from}")
    private String from;

    private JavaMailSender emailSender;
    private HtmlMailer htmlMailer;

    @Autowired
    public EmailsServiceImpl(JavaMailSender emailSender, HtmlMailer htmlMailer) {
        this.emailSender = emailSender;
        this.htmlMailer = htmlMailer;
    }

    @Override
    public void sendMessage(String subject, String text, Accounts accounts) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from);
            simpleMailMessage.setTo(accounts.getEmail());
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(text);

            this.emailSender.send(simpleMailMessage);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void sendHtmlMesseage(String subject, String text, String htmlfile, Map<String, Object> variables, Accounts accounts) {
        try {
            MimeMessage message = this.emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(accounts.getEmail());
            helper.setSubject(subject);
            helper.setText(this.htmlMailer.generateMailHtml(text, htmlfile, variables), true);

            this.emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}