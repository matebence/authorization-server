package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Passwords.class)
public class Passwords implements Serializable {

    private Long passwordTokenId;

    private Accounts accounts;

    private String token;

    private Date expiryDate;

    private Timestamp createdAt;

    public Passwords(Accounts accounts, String token, Date expiryDate) {
        this.accounts = accounts;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public Passwords(String token, Date expiryDate) {
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public Passwords(String token) {
        this.token = token;
    }

    public Passwords() {
    }

    public Long getPasswordTokenId() {
        return this.passwordTokenId;
    }

    public void setPasswordTokenId(Long passwordTokenId) {
        this.passwordTokenId = passwordTokenId;
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

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}