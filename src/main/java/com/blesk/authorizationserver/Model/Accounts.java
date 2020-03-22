package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "accounts")
public class Accounts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,  mappedBy="account")
    private Logins login;

    @JsonIgnore
    @ManyToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name="account_role_items", joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Roles> roles = new HashSet<Roles>();

    @Column(name = "user_id", nullable=false, unique = true)
    private Long userId;

    @Column(name = "user_name", nullable=false, unique = true)
    private String userName;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "balance", nullable=false)
    private double balance;

    @Column(name = "is_activated", nullable=false)
    private boolean isActivated;

    @Column(name = "is_deleted", nullable=false)
    private Boolean isDeleted;

    @Column(name = "created_by", nullable=false)
    private Long createdBy;

    @Column(name = "created_at", updatable=false)
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_by", updatable=false)
    private Long updatedBy;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

    @Column(name = "deleted_at")
    private java.sql.Timestamp deletedAt;

    public Accounts() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Logins getLogin() {
        return login;
    }

    public void setLogin(Logins login) {
        this.login = login;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @PrePersist
    protected void prePersist() {
        this.balance = 0.00;
        this.isActivated = false;
        this.isDeleted = false;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "accountId=" + accountId +
                ", login=" + login +
                ", roles=" + roles +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", isActivated=" + isActivated +
                ", isDeleted=" + isDeleted +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", updatedBy=" + updatedBy +
                ", updatedAt=" + updatedAt +
                ", deletedBy=" + deletedBy +
                ", deletedAt=" + deletedAt +
                '}';
    }
}