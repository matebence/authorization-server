package com.blesk.authorizationserver.Service.Privileges;

import com.blesk.authorizationserver.Model.Privileges;

import java.util.List;

public interface PrivilegesService {

    Privileges createPrivilege(Privileges privileges);

    boolean deletePrivilege(Long privilegeId);

    boolean updatePrivilege(Privileges privileges);

    Privileges getPrivilege(Long Id);

    List<Privileges> getAllPrivileges();

    Privileges getPrivilegeByName(String privilegeName);
}