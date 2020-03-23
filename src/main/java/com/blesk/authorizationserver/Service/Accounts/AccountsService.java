package com.blesk.authorizationserver.Service.Accounts;

import com.blesk.authorizationserver.Model.Accounts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AccountsService {

    Accounts createAccount(Accounts accounts, String[] role);

    boolean deleteAccount(Long accountId);

    boolean updateAccount(Accounts accounts);

    Accounts getAccount(Long id);

    List<Accounts> getAllAccounts(int pageNumber, int pageSize);

    Accounts getAccountInformations(String userName);

    Map<String, Object> searchForAccount(HashMap<String, HashMap<String, String>> criteria);
}