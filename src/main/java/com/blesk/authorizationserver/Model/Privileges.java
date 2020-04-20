package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Privileges.class)
public class Privileges implements Serializable {

    private Long privilegeId;

    private Set<RolePrivileges> rolePrivileges = new HashSet<RolePrivileges>();

    private String name;

    private Boolean isDeleted = false;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public Privileges(String name, Boolean isDeleted) {
        this.name = name;
        this.isDeleted = isDeleted;
    }

    public Privileges() {
    }

    public Long getPrivilegeId() {
        return this.privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public void addRole(RolePrivileges rolePrivileges) {
        this.rolePrivileges.add(rolePrivileges);
    }

    public void removeRole(RolePrivileges rolePrivileges) {
        this.rolePrivileges.remove(rolePrivileges);
    }

    public Set<RolePrivileges> getRolePrivileges() {
        return this.rolePrivileges;
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