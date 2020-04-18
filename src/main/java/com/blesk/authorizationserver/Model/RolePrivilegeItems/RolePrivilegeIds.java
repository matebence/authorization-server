package com.blesk.authorizationserver.Model.RolePrivilegeItems;

import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Model.Roles;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = RolePrivilegeIds.class)
public class RolePrivilegeIds implements Serializable {

    private Roles roles;

    private Privileges privileges;

    public RolePrivilegeIds() {
    }

    public Roles getRoles(){
        return this.roles;
    }

    public void setRoles(Roles roles){
        this.roles = roles;
    }

    public Privileges getPrivileges(){
        return this.privileges;
    }

    public void setPrivileges(Privileges privileges){
        this.privileges = privileges;
    }
}