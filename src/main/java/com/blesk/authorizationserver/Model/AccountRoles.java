package com.blesk.authorizationserver.Model.AccountRoleItems;

import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Roles;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = AccountRoles.class)
public class AccountRoles implements Serializable {

    private AccountRoleIds accountRoleIds = new AccountRoleIds();

    public AccountRoles() {
    }

    public AccountRoleIds getAccountRoleIds() {
        return this.accountRoleIds;
    }

    public void setAccountRoleIds(AccountRoleIds accountRoleIds) {
        this.accountRoleIds = accountRoleIds;
    }

    public Accounts getAccounts() {
        return getAccountRoleIds().getAccounts();
    }

    public void setAccounts(Accounts accounts) {
        getAccountRoleIds().setAccounts(accounts);
    }

    public Roles getRoles() {
        return getAccountRoleIds().getRoles();
    }

    public void setRoles(Roles roles) {
        getAccountRoleIds().setRoles(roles);
    }
}