package com.blesk.authorizationserver.Service.Privileges;

import com.blesk.authorizationserver.DAO.Privileges.PrivilegesDAOImpl;
import com.blesk.authorizationserver.Exceptions.AuthorizationServerException;
import com.blesk.authorizationserver.Model.Privileges;
import com.blesk.authorizationserver.Values.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegesServiceImpl implements PrivilegesService {

    private PrivilegesDAOImpl privilegeDAO;

    @Autowired
    public PrivilegesServiceImpl(PrivilegesDAOImpl privilegeDAO){
        this.privilegeDAO = privilegeDAO;
    }

    @Override
    public Privileges createPrivilege(Privileges privileges) {
        if (this.privilegeDAO.save(privileges).getPrivilegeId() == null)
            throw new AuthorizationServerException(Messages.CREATE_PRIVILEGE);
        return privileges;
    }

    @Override
    public boolean deletePrivilege(Long privilegeId) {
        Privileges privileges = this.privilegeDAO.get(Privileges.class, privilegeId);
        if(privileges == null)
            throw new AuthorizationServerException(Messages.DELETE_GET_PRIVILEGE);
        if (!this.privilegeDAO.delete(privileges))
            throw new AuthorizationServerException(Messages.DELETE_PRIVILEGE);
        return true;
    }

    @Override
    public boolean updatePrivilege(Privileges privileges) {
        if (!this.privilegeDAO.update(privileges))
            throw new AuthorizationServerException(Messages.UPDATE_PRIVILEGE);
        return true;
    }

    @Override
    public Privileges getPrivilege(Long privilegeId) {
        Privileges privilege = this.privilegeDAO.get(Privileges.class, privilegeId);
        if(privilege == null)
            throw new AuthorizationServerException(Messages.GET_PRIVILEGE);
        return privilege;
    }

    @Override
    public List<Privileges> getAllPrivileges(int pageNumber, int pageSize) {
        List<Privileges> privileges = this.privilegeDAO.getAll(Privileges.class, pageNumber, pageSize);
        if(privileges == null)
            throw new AuthorizationServerException(Messages.GET_ALL_PRIVILEGES);
        return privileges;
    }

    @Override
    public Privileges getPrivilegeByName(String privilegeName) {
        Privileges privilege = this.privilegeDAO.getPrivilegeByName(privilegeName);
        if(privilege == null)
            throw new AuthorizationServerException(Messages.GET_PRIVILEGE_BY_NAME);
        return privilege;
    }
}