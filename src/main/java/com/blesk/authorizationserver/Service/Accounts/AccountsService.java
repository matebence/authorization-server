package com.blesk.authorizationserver.Service.Accounts;

import com.blesk.authorizationserver.Model.Accounts;

import java.util.HashMap;
import java.util.List;

public interface AccountsService {

    Accounts createAccount(Accounts accounts, String[] role);

    boolean deleteAccount(Long accountId);

    boolean updateAccount(Accounts accounts);

    Accounts getAccount(Long id);

    Accounts getAccountInformations(String userName);

    List<Accounts> searchForAccount(HashMap<String, String> orderByColumn, HashMap<String, String> searchByColumns);
}