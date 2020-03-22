package com.blesk.authorizationserver.Service.Privileges;

import com.blesk.authorizationserver.DAO.Privileges.PrivilegesDAOImpl;
import com.blesk.authorizationserver.Exceptions.AuthorizationServerException;
import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Utility.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegesServiceImpl implements PrivilegesService {

    private PrivilegesDAOImpl privilegeDAO;
    private ResourceLoader resourceLoader;

    @Autowired
    public PrivilegesServiceImpl(PrivilegesDAOImpl privilegeDAO, ResourceLoader resourceLoader){
        this.privilegeDAO = privilegeDAO;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Privileges createPrivilege(Privileges privileges) {
        if (privilegeDAO.save(privileges).getPrivilegeId() == null)
            throw new AuthorizationServerException(Messages.CREATE_PRIVILEGE);
        return privileges;
    }

    @Override
    public boolean deletePrivilege(Long privilegeId) {
        Privileges privileges = privilegeDAO.get(Privileges.class, privilegeId);
        if(privileges == null)
            throw new AuthorizationServerException(Messages.DELETE_GET_PRIVILEGE);
        if (!privilegeDAO.delete(privileges))
            throw new AuthorizationServerException(Messages.DELETE_PRIVILEGE);
        return true;
    }

    @Override
    public boolean updatePrivilege(Privileges privileges) {
        if (!privilegeDAO.update(privileges))
            throw new AuthorizationServerException(Messages.UPDATE_PRIVILEGE);
        return true;
    }

    @Override
    public Privileges getPrivilege(Long id) {
        Privileges privilege = privilegeDAO.get(Privileges.class, id);
        if(privilege == null)
            throw new AuthorizationServerException(Messages.GET_PRIVILEGE);
        return privilege;
    }

    @Override
    public List<Privileges> getAllPrivileges() {
        List<Privileges> privileges = privilegeDAO.getAll(Privileges.class);
        if(privileges == null)
            throw new AuthorizationServerException(Messages.GET_ALL_PRIVILEGES);
        return privileges;
    }

    @Override
    public Privileges getPrivilegeByName(String privilegeName) {
        Privileges privilege = privilegeDAO.getPrivilegeByName(privilegeName);
        if(privilege == null)
            throw new AuthorizationServerException(Messages.GET_PRIVILEGE_BY_NAME);
        return privilege;
    }
}