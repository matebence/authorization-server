package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = AccountPreferences.class)
public class AccountPreferences implements Serializable {

    private Long accountPreferenceId;

    private Accounts accounts;

    private Preferences preferences;

    private boolean isSet;

    private String content;

    private int value;

    public AccountPreferences(boolean isSet, String content, int value, Accounts accounts, Preferences preferences) {
        this.isSet = isSet;
        this.content = content;
        this.value = value;
        this.accounts = accounts;
        this.preferences = preferences;
    }

    public AccountPreferences(boolean isSet, String content, int value) {
        this.isSet = isSet;
        this.content = content;
        this.value = value;
    }

    public AccountPreferences(Accounts accounts, Preferences preferences) {
        this.accounts = accounts;
        this.preferences = preferences;
    }

    public AccountPreferences(Accounts accounts) {
        this.accounts = accounts;
    }

    public AccountPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public AccountPreferences() {
    }

    public Long getAccountPreferenceId() {
        return this.accountPreferenceId;
    }

    public void setAccountPreferenceId(Long accountPreferenceId) {
        this.accountPreferenceId = accountPreferenceId;
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
}