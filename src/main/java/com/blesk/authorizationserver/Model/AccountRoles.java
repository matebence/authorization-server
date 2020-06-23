package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = AccountRoles.class)
public class AccountRoles implements Serializable {

    private Long accountRoleId;

    private Accounts accounts;

    private Roles roles;

    public AccountRoles(Accounts accounts, Roles roles) {
        this.accounts = accounts;
        this.roles = roles;
    }

    public AccountRoles(Accounts accounts) {
        this.accounts = accounts;
    }

    public AccountRoles(Roles roles) {
        this.roles = roles;
    }

    public AccountRoles() {
    }

    public Long getAccountRoleId() {
        return this.accountRoleId;
    }

    public void setAccountRoleId(Long accountRoleId) {
        this.accountRoleId = accountRoleId;
    }

    public Accounts getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Roles getRoles() {
        return this.roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}