package com.blesk.authorizationserver.DTO;

import com.blesk.authorizationserver.Model.Accounts;
import org.springframework.security.core.userdetails.User;

public class JwtResponse extends User {

    private Long accountId;
    private String userName;
    private Double balance;
    private Boolean isActivated;

    public JwtResponse(Accounts accounts) {
        super(accounts.getUserName(), accounts.getPassword(), accounts.getGrantedAuthorities());
        this.accountId = accounts.getAccountId();
        this.userName = accounts.getUserName();
        this.balance = accounts.getBalance();
        this.isActivated = accounts.getActivated();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "accountId=" + accountId +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                ", isActivated=" + isActivated +
                '}';
    }
}
