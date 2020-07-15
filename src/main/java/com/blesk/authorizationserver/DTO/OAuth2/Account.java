package com.blesk.authorizationserver.DTO.OAuth2;

import com.blesk.authorizationserver.Model.Accounts;
import org.springframework.security.core.userdetails.User;

public class Account extends User {

    private Long accountId;
    private String userName;

    public Account(Accounts accounts) {
        super(accounts.getUserName(), accounts.getPassword(), accounts.getGrantedAuthorities());
        this.accountId = accounts.getAccountId();
        this.userName = accounts.getUserName();
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}