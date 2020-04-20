package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Roles.class)
public class Roles implements Serializable {

    private Long roleId;

    private Set<AccountRoles> accountRoles = new HashSet<AccountRoles>();

    private Set<RolePrivileges> rolePrivileges = new HashSet<RolePrivileges>();

    private String name;

    private Boolean isDeleted = false;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public Roles(String name, Boolean isDeleted) {
        this.name = name;
        this.isDeleted = isDeleted;
    }

    public Roles() {
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void addPrivilege(RolePrivileges rolePrivileges) {
        this.rolePrivileges.add(rolePrivileges);
    }

    public void removePrivilege(RolePrivileges rolePrivileges) {
        this.rolePrivileges.remove(rolePrivileges);
    }

    public Set<RolePrivileges> getRolePrivileges() {
        return this.rolePrivileges;
    }

    public void addAccount(AccountRoles accountRoles) {
        this.accountRoles.add(accountRoles);
    }

    public void removeAccount(AccountRoles accountRoles) {
        this.accountRoles.remove(accountRoles);
    }

    public Set<AccountRoles> getAccountRoles() {
        return this.accountRoles;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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