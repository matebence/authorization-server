package com.blesk.authorizationserver.Model.AccountPreferenceItems;

import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Preferences;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = AccountPreferenceIds.class)
public class AccountPreferenceIds implements Serializable {

    private Accounts accounts;

    private Preferences preferences;

    public AccountPreferenceIds() {
    }

    public Accounts getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Preferences getPreferences() {
        return this.preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
}