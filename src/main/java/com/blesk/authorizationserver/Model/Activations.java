package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Activations.class)
public class Activations implements Serializable {

    private Long accountActivationId;

    private Accounts accounts;

    private String token;

    private Boolean isDeleted;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public Activations(Accounts accounts, String token, Boolean isDeleted) {
        this.accounts = accounts;
        this.token = token;
        this.isDeleted = isDeleted;
    }

    public Activations(Accounts accounts, String token) {
        this.accounts = accounts;
        this.token = token;
    }

    public Activations(String token) {
        this.token = token;
    }

    public Activations() {
    }

    public Long getAccountActivationId() {
        return this.accountActivationId;
    }

    public void setAccountActivationId(Long accountActivationId) {
        this.accountActivationId = accountActivationId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Accounts getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
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