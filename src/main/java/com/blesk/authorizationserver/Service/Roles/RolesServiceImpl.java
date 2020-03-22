package com.blesk.authorizationserver.Service.Roles;

import com.blesk.authorizationserver.DAO.Roles.RolesDAOImpl;
import com.blesk.authorizationserver.Exceptions.AuthorizationServerException;
import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Model.Roles;
import com.blesk.authorizationserver.Utility.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    private RolesDAOImpl roleDAO;
    private ResourceLoader resourceLoader;

    @Autowired
    public RolesServiceImpl(RolesDAOImpl roleDAO, ResourceLoader resourceLoader){
        this.roleDAO = roleDAO;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Roles createRole(Roles roles) {
        if (roleDAO.save(roles).getRoleId() == null)
            throw new AuthorizationServerException(Messages.CREATE_ROLE);
        return roles;
    }

    @Override
    public boolean deleteRole(Long roleId) {
        Roles roles = roleDAO.get(Roles.class, roleId);
        if(roles == null)
            throw new AuthorizationServerException(Messages.DELETE_GET_ROLE);
        if (!roleDAO.delete(roles))
            throw new AuthorizationServerException(Messages.DELETE_ROLE);
        return true;
    }

    @Override
    public boolean updateRole(Roles roles) {
        if (!roleDAO.update(roles))
            throw new AuthorizationServerException(Messages.UPDATE_ROLE);
        return true;
    }

    @Override
    public Roles getRole(Long id) {
        Roles role = roleDAO.get(Roles.class, id);
        if(role == null)
            throw new AuthorizationServerException(Messages.GET_ROLE);
        return role;
    }

    @Override
    public List<Roles> getAllRoles() {
        List<Roles> roles = roleDAO.getAll(Roles.class);
        if(roles == null)
            throw new AuthorizationServerException(Messages.GET_ALL_ROLES);
        return roles;
    }

    @Override
    public Roles getRoleByName(String roleName) {
        Roles role = roleDAO.getRoleByName(roleName);
        if(role == null)
            throw new AuthorizationServerException(Messages.GET_ROLE_BY_NAME);
        return role;
    }

    @Override
    public List<Privileges> getRolePrivileges(String roleName) {
        List<Privileges> privileges = roleDAO.getPrivilegesAssignedToRole(roleName);
        if (privileges.isEmpty())
            throw new AuthorizationServerException(String.format(Messages.GET_ROLE_PRIVILEGES, roleName));
        return privileges;
    }
}
