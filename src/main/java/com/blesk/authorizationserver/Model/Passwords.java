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

    private Boolean isDeleted;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public Passwords(Accounts accounts, String token, Boolean isDeleted) {
        this.accounts = accounts;
        this.token = token;
        this.isDeleted = isDeleted;
        this.expiryDate = expiryDate;
    }

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

    public Boolean getDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        this.isDeleted = deleted;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}