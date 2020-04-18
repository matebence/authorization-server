package com.blesk.authorizationserver.DTO.OAuth2;

import com.blesk.authorizationserver.Model.Accounts;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class Account extends User {

    private Long accountId;
    private Long loginId;
    private String userName;
    private Boolean isActivated;
    private Collection<String> grantedPrivileges;

    public Account(Accounts accounts) {
        super(accounts.getUserName(), accounts.getPassword(), accounts.getGrantedAuthorities());
        this.accountId = accounts.getAccountId();
        this.loginId = accounts.getLogin().getLoginId();
        this.userName = accounts.getUserName();
        this.isActivated = accounts.getActivated();
        this.grantedPrivileges = accounts.getGrantedPrivileges();
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

    public Collection<String> getGrantedPrivileges() {
        return this.grantedPrivileges;
    }

    public void setGrantedPrivileges(Collection<String> grantedPrivileges) {
        this.grantedPrivileges = grantedPrivileges;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", loginId=" + loginId +
                ", userName='" + userName + '\'' +
                ", isActivated=" + isActivated +
                ", grantedPrivileges=" + grantedPrivileges +
                '}';
    }
}