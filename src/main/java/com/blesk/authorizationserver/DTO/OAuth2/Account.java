package com.blesk.authorizationserver.DTO.OAuth2;

import com.blesk.authorizationserver.Model.Accounts;
import org.springframework.security.core.userdetails.User;

public class Account extends User {

    private Long accountId;
    private Long loginId;
    private String userName;
    private Boolean isActivated;

    public Account(Accounts accounts) {
        super(accounts.getUserName(), accounts.getPassword(), accounts.getGrantedAuthorities());
        this.accountId = accounts.getAccountId();
        this.loginId = accounts.getLogin().getLoginId();
        this.userName = accounts.getUserName();
        this.isActivated = accounts.getActivated();
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getLoginId() {
        return this.loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean isActivated() {
        return this.isActivated;
    }

    public void setActivated(boolean activated) {
        this.isActivated = activated;
    }
}