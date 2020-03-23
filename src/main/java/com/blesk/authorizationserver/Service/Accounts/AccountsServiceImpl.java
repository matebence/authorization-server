package com.blesk.authorizationserver.Service.Accounts;

import com.blesk.authorizationserver.DAO.Accounts.AccountsDAOImpl;
import com.blesk.authorizationserver.DAO.Roles.RolesDAOImpl;
import com.blesk.authorizationserver.Exceptions.AuthorizationServerException;
import com.blesk.authorizationserver.Model.Accounts;
import com.blesk.authorizationserver.Model.Roles;
import com.blesk.authorizationserver.Values.Criteria;
import com.blesk.authorizationserver.Values.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountsServiceImpl implements AccountsService {

    private AccountsDAOImpl accountDAO;
    private RolesDAOImpl roleDAO;

    @Autowired
    public AccountsServiceImpl(AccountsDAOImpl accountDAO, RolesDAOImpl roleDAO) {
        this.accountDAO = accountDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public Accounts createAccount(Accounts accounts, String[] role) {
        List<Roles> roles = roleDAO.getListOfRoles(role);
        if (roles.isEmpty())
            throw new AuthorizationServerException(Messages.CREATE_GET_ACCOUNT);
        accounts.setRoles(roles);
        if (accountDAO.save(accounts).getAccountId() == null)
            throw new AuthorizationServerException(Messages.CREATE_ACCOUNT);
        return accounts;
    }

    @Override
    public boolean deleteAccount(Long accountId) {
        Accounts accounts = accountDAO.get(Accounts.class, accountId);
        if (accounts == null)
            throw new AuthorizationServerException(Messages.DELETE_GET_ACCOUNT);
        if (!accountDAO.delete(accounts))
            throw new AuthorizationServerException(Messages.DELETE_ACCOUNT);
        return true;
    }

    @Override
    public boolean updateAccount(Accounts accounts) {
        if (!accountDAO.update(accounts))
            throw new AuthorizationServerException(Messages.UPDATE_ACCOUNT);
        return true;
    }

    @Override
    public Accounts getAccount(Long id) {
        Accounts accounts = accountDAO.get(Accounts.class, id);
        if (accounts == null)
            throw new AuthorizationServerException(Messages.GET_ACCOUNT);
        return accounts;
    }

    @Override
    public List<Accounts> getAllAccounts(int pageNumber, int pageSize) {
        List<Accounts> accounts = accountDAO.getAll(Accounts.class, pageNumber, pageSize);
        if (accounts.isEmpty())
            throw new AuthorizationServerException(Messages.GET_ALL_ACCOUNTS);
        return accounts;
    }

    @Override
    public Accounts getAccountInformations(String userName) {
        Accounts accounts = accountDAO.getAccountInformations(userName);
        if (accounts == null)
            throw new AuthorizationServerException(Messages.GET_ACCOUNT_INFORMATION);
        return accounts;
    }

    @Override
    public Map<String, Object> searchForAccount(HashMap<String, HashMap<String, String>> criteria) {
        if (criteria.get(Criteria.PAGINATION) == null)
            throw new NullPointerException(Messages.PAGINATION_EXCEPTION);

        Map<String, Object> accounts = accountDAO.searchBy(criteria, Integer.parseInt(criteria.get(Criteria.PAGINATION).get(Criteria.PAGE_NUMBER)));

        if (accounts.isEmpty())
            throw new AuthorizationServerException(Messages.SEARCH_FOR_ACCOUNT);

        return accounts;
    }
}