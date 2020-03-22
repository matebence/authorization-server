package com.blesk.authorizationserver.Service.Privileges;

import com.blesk.authorizationserver.DAO.Privileges.PrivilegeDAOImpl;
import com.blesk.authorizationserver.Exceptions.AuthorizationServerException;
import com.blesk.authorizationserver.Model.Privileges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    private PrivilegeDAOImpl privilegeDAO;
    private ResourceLoader resourceLoader;

    @Autowired
    public PrivilegeServiceImpl(PrivilegeDAOImpl privilegeDAO, ResourceLoader resourceLoader){
        this.privilegeDAO = privilegeDAO;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Privileges createPrivilege(Privileges privileges) {
        if (privilegeDAO.save(privileges).getPrivilegeId() == null)
            throw new AuthorizationServerException(resourceLoader.getResource("createprivilege.messeage").toString());
        return privileges;
    }

    @Override
    public boolean deletePrivilege(Long privilegeId) {
        Privileges privileges = privilegeDAO.get(Privileges.class, privilegeId);
        if(privileges == null)
            throw new AuthorizationServerException(resourceLoader.getResource("deleteprivilege.getprivelege.messeage").toString());
        if (!privilegeDAO.delete(privileges))
            throw new AuthorizationServerException(resourceLoader.getResource("deleteprivilege.messeage").toString());
        return true;
    }

    @Override
    public boolean updatePrivilege(Privileges privileges) {
        if (!privilegeDAO.update(privileges))
            throw new AuthorizationServerException(resourceLoader.getResource("updateprivilege.messeage").toString());
        return true;
    }

    @Override
    public Privileges getPrivilege(Long id) {
        Privileges privilege = privilegeDAO.get(Privileges.class, id);
        if(privilege == null)
            throw new AuthorizationServerException(resourceLoader.getResource("getprivilege.messeage").toString());
        return privilege;
    }

    @Override
    public Privileges getPrivilegeByName(String privilegeName) {
        Privileges privilege = privilegeDAO.getPrivilegeByName(privilegeName);
        if(privilege == null)
            throw new AuthorizationServerException(resourceLoader.getResource("getPrivilegeByName.messeage").toString());
        return privilege;
    }
}