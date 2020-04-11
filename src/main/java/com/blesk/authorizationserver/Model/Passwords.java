package com.blesk.authorizationserver.DTO.RabbitMQ;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Passwords.class)
public class Passwords implements Serializable {

    private static final int EXPIRATION = 60 * 24;

    private Long passwordResetTokenId;

    private String token;

    private Accounts account;

    private Date expiryDate;

    public Passwords() {
    }

    public Long getPasswordResetTokenId() {
        return this.passwordResetTokenId;
    }

    public void setPasswordResetTokenId(Long passwordResetTokenId) {
        this.passwordResetTokenId = passwordResetTokenId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Accounts getAccount() {
        return this.account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate() {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, Passwords.EXPIRATION);
        this.expiryDate = new Date(cal.getTime().getTime());
    }
}