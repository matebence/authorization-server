package com.blesk.authorizationserver.DAO.Roles;

import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Model.Roles;

import java.util.List;

public interface RoleDAO {
    List<Roles> getListOfRoles(String[] names);

    Roles getRoleByName(String name);

    List<Privileges> getPrivilegesAssignedToRole(String name);
}
