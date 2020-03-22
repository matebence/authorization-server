package com.blesk.authorizationserver.Service.Privileges;

import com.blesk.authorizationserver.Model.Privileges;

public interface PrivilegesService {

    Privileges createPrivilege(Privileges privileges);

    boolean deletePrivilege(Long privilegeId);

    boolean updatePrivilege(Privileges privileges);

    Privileges getPrivilege(Long Id);

    Privileges getPrivilegeByName(String privilegeName);
}