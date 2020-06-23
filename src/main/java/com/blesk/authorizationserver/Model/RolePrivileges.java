package com.blesk.authorizationserver.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = RolePrivileges.class)
public class RolePrivileges implements Serializable {

    private Long rolePrivilegeId;

    private Roles roles;

    private Privileges privileges;

    public RolePrivileges(Roles roles, Privileges privileges) {
        this.roles = roles;
        this.privileges = privileges;
    }

    public RolePrivileges(Roles roles) {
        this.roles = roles;
    }

    public RolePrivileges(Privileges privileges) {
        this.privileges = privileges;
    }

    public RolePrivileges() {
    }

    public Long getRolePrivilegeId() {
        return this.rolePrivilegeId;
    }

    public void setRolePrivilegeId(Long rolePrivilegeId) {
        this.rolePrivilegeId = rolePrivilegeId;
    }

    public Roles getRoles() {
        return this.roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Privileges getPrivileges() {
        return this.privileges;
    }

    public void setPrivileges(Privileges privileges) {
        this.privileges = privileges;
    }
}