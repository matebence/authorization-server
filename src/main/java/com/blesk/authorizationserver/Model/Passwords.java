package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Passwords.class)
public class Passwords implements Serializable {

    private Long passwordResetTokenId;

    private String token;

    private Accounts accounts;

    private Date expiryDate;

    public Passwords(Accounts accounts, String token, Date expiryDate) {
        this.accounts = accounts;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public Passwords(String token, Date expiryDate) {
        this.token = token;
        this.expiryDate = expiryDate;
    }

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

    public Accounts getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}