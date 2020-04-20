package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = AccountPreferences.class)
public class AccountPreferences implements Serializable {

    private Long accountPreferenceId;

    private Accounts accounts;

    private Preferences preferences;

    private boolean isSet;

    private String content;

    private int value;

    private Boolean isDeleted;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

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

    public AccountPreferences(boolean isSet, String content, int value, Boolean isDeleted) {
        this.isSet = isSet;
        this.content = content;
        this.value = value;
        this.isDeleted = isDeleted;
    }

    public AccountPreferences(boolean isSet, String content, int value, Boolean isDeleted, Accounts accounts, Preferences preferences) {
        this.isSet = isSet;
        this.content = content;
        this.value = value;
        this.isDeleted = isDeleted;
        this.accounts = accounts;
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