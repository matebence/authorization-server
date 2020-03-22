package com.blesk.authorizationserver.Service.Accounts;

import com.blesk.authorizationserver.DAO.Accounts.AccountsDAOImpl;
import com.blesk.authorizationserver.DAO.Roles.RolesDAOImpl;
import com.blesk.authorizationserver.Exceptions.AuthorizationServerException;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Roles;
import com.blesk.authorizationserver.Utility.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService {

    private AccountsDAOImpl accountDAO;
    private RolesDAOImpl roleDAO;
    private ResourceLoader resourceLoader;

    @Autowired
    public AccountsServiceImpl(AccountsDAOImpl accountDAO, RolesDAOImpl roleDAO, ResourceLoader resourceLoader){
        this.accountDAO = accountDAO;
        this.roleDAO = roleDAO;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Accounts createAccount(Accounts accounts, String[] role) {
        List<Roles> roles = roleDAO.getListOfRoles(role);
        if (roles.isEmpty())
            throw new AuthorizationServerException(resourceLoader.getResource("createaccount.getrole.messeage").toString());
        accounts.setRoles(roles);
        if (accountDAO.save(accounts).getAccountId() == null)
            throw new AuthorizationServerException(resourceLoader.getResource("createaccount.messeage").toString());
        return accounts;
    }

    @Override
    public boolean deleteAccount(Long accountId) {
        Accounts accounts = accountDAO.get(Accounts.class, accountId);
        if(accounts == null)
            throw new AuthorizationServerException(resourceLoader.getResource("deleteaccount.getaccount.messeage").toString());
        if (!accountDAO.delete(accounts))
            throw new AuthorizationServerException(resourceLoader.getResource("deleteaccount.messeage").toString());
        return true;
    }

    @Override
    public boolean updateAccount(Accounts accounts) {
        if (!accountDAO.update(accounts))
            throw new AuthorizationServerException(resourceLoader.getResource("updateaccount.messeage").toString());
        return true;
    }

    @Override
    public Accounts getAccount(Long id) {
        Accounts accounts = accountDAO.get(Accounts.class, id);
        if(accounts == null)
            throw new AuthorizationServerException(resourceLoader.getResource("getaccount.messeage").toString());
        return accounts;
    }


    @Override
    public Accounts getAccountInformations(String userName) {
        Accounts accounts = accountDAO.getAccountInformations(userName);
        if(accounts == null)
            throw new AuthorizationServerException(resourceLoader.getResource("getAccountInformations.messeage").toString());
        return accounts;
    }

    @Override
    public List<Accounts> searchForAccount(HashMap<String, String> orderByColumn, HashMap<String, String> searchByColumns) {
        HashMap<String, HashMap<String, String>> criteria = new HashMap<>();
        criteria.put(Criteria.ORDER_BY, orderByColumn);
        criteria.put(Criteria.SEARCH, searchByColumns);
        List<Accounts> accounts = accountDAO.searchBy(Accounts.class, criteria);

        if (accounts.isEmpty())
            throw new AuthorizationServerException(resourceLoader.getResource("searchforaccount.messeage").toString());

        return accounts;
    }
}