package com.blesk.authorizationserver.Service.Roles;

import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Model.Roles;

import java.util.List;

public interface RolesService {

    Roles createRole(Roles roles);

    boolean deleteRole(Long roleId);

    boolean updateRole(Roles roles);

    Roles getRole(Long id);

    List<Roles> getAllRoles();

    Roles getRoleByName(String roleName);

    List<Privileges> getRolePrivileges(String roleName);
}