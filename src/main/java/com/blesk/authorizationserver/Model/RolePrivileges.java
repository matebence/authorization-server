package com.blesk.authorizationserver.Model.RolePrivilegeItems;

import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Model.Roles;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = RolePrivileges.class)
public class RolePrivileges implements Serializable {

    private RolePrivilegeIds rolePrivilegeIds = new RolePrivilegeIds();

    public RolePrivileges() {
    }

    public RolePrivilegeIds getRolePrivilegeIds() {
        return this.rolePrivilegeIds;
    }

    public void setRolePrivilegeIds(RolePrivilegeIds rolePrivilegeIds) {
        this.rolePrivilegeIds = rolePrivilegeIds;
    }

    public Roles getRoles() {
        return getRolePrivilegeIds().getRoles();
    }

    public void setRoles(Roles roles) {
        getRolePrivilegeIds().setRoles(roles);
    }

    public Privileges getPrivileges() {
        return getRolePrivilegeIds().getPrivileges();
    }

    public void setPrivileges(Privileges privileges) {
        getRolePrivilegeIds().setPrivileges(privileges);
    }
}