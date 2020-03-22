package com.blesk.authorizationserver.DAO.Privileges;

import com.blesk.authorizationserver.Model.Privileges;

public interface PrivilegeDAO {

    Privileges getPrivilegeByName(String name);
}
