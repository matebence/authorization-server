package com.blesk.authorizationserver.Model.AccountRoleItems;

import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Roles;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = AccountRoleIds.class)
public class AccountRoleIds implements Serializable {

    private Accounts accounts;

    private Roles roles;

    public AccountRoleIds() {
    }

    public AccountRoleIds(Accounts accounts, Roles roles) {
        this.accounts = accounts;
        this.roles = roles;
    }

    public Accounts getAccounts(){
        return this.accounts;
    }

    public void setAccounts(Accounts accounts){
        this.accounts = accounts;
    }

    public Roles getRoles(){
        return this.roles;
    }

    public void setRoles(Roles roles){
        this.roles = roles;
    }
}