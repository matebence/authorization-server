package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Logins.class)
public class Logins implements Serializable {

    private Long loginId;

    private Accounts accounts;

    private Timestamp lastLogin = new Timestamp(System.currentTimeMillis());

    private String ipAddress;

    private Boolean isDeleted = false;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public Logins(Accounts accounts, Timestamp lastLogin, String ipAddress, Boolean isDeleted) {
        this.accounts = accounts;
        this.lastLogin = lastLogin;
        this.ipAddress = ipAddress;
        this.isDeleted = isDeleted;
    }

    public Logins(Accounts accounts, Timestamp lastLogin, String ipAddress) {
        this.accounts = accounts;
        this.lastLogin = lastLogin;
        this.ipAddress = ipAddress;
    }

    public Logins(Timestamp lastLogin, String ipAddress) {
        this.lastLogin = lastLogin;
        this.ipAddress = ipAddress;
    }

    public Logins() {
    }

    public Long getLoginId() {
        return this.loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public Accounts getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Timestamp getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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