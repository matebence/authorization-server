package com.blesk.authorizationserver.Service.Roles;

import com.blesk.authorizationserver.DAO.Roles.RoleDAOImpl;
import com.blesk.authorizationserver.Exceptions.AuthorizationServerException;
import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDAOImpl roleDAO;
    private ResourceLoader resourceLoader;

    @Autowired
    public RoleServiceImpl(RoleDAOImpl roleDAO, ResourceLoader resourceLoader){
        this.roleDAO = roleDAO;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Roles createRole(Roles roles) {
        if (roleDAO.save(roles).getRoleId() == null)
            throw new AuthorizationServerException(resourceLoader.getResource("createrole.messeage").toString());
        return roles;
    }

    @Override
    public boolean deleteRole(Long roleId) {
        Roles roles = roleDAO.get(Roles.class, roleId);
        if(roles == null)
            throw new AuthorizationServerException(resourceLoader.getResource("deleterole.getrole.messeage").toString());
        if (!roleDAO.delete(roles))
            throw new AuthorizationServerException(resourceLoader.getResource("deleterole.messeage").toString());
        return true;
    }

    @Override
    public boolean updateRole(Roles roles) {
        if (!roleDAO.update(roles))
            throw new AuthorizationServerException(resourceLoader.getResource("updaterole.messeage").toString());
        return true;
    }

    @Override
    public Roles getRole(Long id) {
        Roles role = roleDAO.get(Roles.class, id);
        if(role == null)
            throw new AuthorizationServerException(resourceLoader.getResource("getrole.messeage").toString());
        return role;
    }

    @Override
    public Roles getRoleByName(String roleName) {
        Roles role = roleDAO.getRoleByName(roleName);
        if(role == null)
            throw new AuthorizationServerException(resourceLoader.getResource("getRoleByName.messeage").toString());
        return role;
    }

    @Override
    public List<Privileges> getRolePrivileges(String roleName) {
        List<Privileges> privileges = roleDAO.getPrivilegesAssignedToRole(roleName);
        if (privileges.isEmpty())
            throw new AuthorizationServerException(String.format(resourceLoader.getResource("getroleprivileges.messeage").toString(), roleName));
        return privileges;
    }
}
