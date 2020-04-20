package com.blesk.authorizationserver.Model.AccountPreferenceItems;

import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Preferences;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = AccountPreferences.class)
public class AccountPreferences implements Serializable {

    private AccountPreferenceIds accountPreferenceIds = new AccountPreferenceIds();

    private boolean isSet;

    private String content;

    private int value;

    private Boolean isDeleted;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public AccountPreferences() {
    }

    public AccountPreferenceIds getAccountPreferenceIds() {
        return this.accountPreferenceIds;
    }

    public void setAccountPreferenceIds(AccountPreferenceIds accountPreferenceIds) {
        this.accountPreferenceIds = accountPreferenceIds;
    }

    public Accounts getAccounts() {
        return getAccountPreferenceIds().getAccounts();
    }

    public void setAccounts(Accounts accounts) {
        getAccountPreferenceIds().setAccounts(accounts);
    }

    public Preferences getPreferences() {
        return getAccountPreferenceIds().getPreferences();
    }

    public void setPreferences(Preferences preferences) {
        getAccountPreferenceIds().setPreferences(preferences);
    }

    public boolean isSet() {
        return this.isSet;
    }

    public void setSet(boolean set) {
        this.isSet = set;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Boolean getDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        this.isDeleted = deleted;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }
}