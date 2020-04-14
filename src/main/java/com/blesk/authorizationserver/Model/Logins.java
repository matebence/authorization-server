package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Logins.class)
public class Logins implements Serializable {

    private Long loginId;

    private Accounts accounts;

    private Timestamp lastLogin;

    private String ipAddress;

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
}