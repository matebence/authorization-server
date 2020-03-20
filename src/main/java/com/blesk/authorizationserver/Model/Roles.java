package com.blesk.authorizationserver.Model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Where(clause="is_deleted=FALSE")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="role_privilege_items", joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="privilege_id"))
    private Set<Privileges> privileges = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Set<Accounts> accounts = new HashSet<Accounts>();

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "is_deleted", nullable=false)
    private Boolean isDeleted = false;

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

    public Roles() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Set<Privileges> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privileges> privileges) {
        this.privileges = privileges;
    }

    public Set<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Accounts> accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.isDeleted = false;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleId=" + roleId +
                ", privileges=" + privileges +
                ", accounts=" + accounts +
                ", name='" + name + '\'' +
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
