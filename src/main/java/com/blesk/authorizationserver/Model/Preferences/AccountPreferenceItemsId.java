package com.blesk.authorizationserver.Model.Preferences;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = AccountPreferenceItemsId.class)
public class AccountPreferenceItemsId implements Serializable {

    private Long preferenceId;

    private Long accountId;

    public AccountPreferenceItemsId() {
    }

    public Long getPreferenceId() {
        return this.preferenceId;
    }

    public void setPreferenceId(Long preferenceId) {
        this.preferenceId = preferenceId;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}