package com.blesk.authorizationserver.Service.Accounts;

import com.blesk.authorizationserver.Model.Accounts;

import java.util.HashMap;
import java.util.List;

public interface AccountService {

    Accounts createAccount(Accounts accounts, String[] role);

    boolean deleteAccount(Long accountId);

    boolean updateAccount(Accounts accounts);

    List<Accounts> searchForAccount(HashMap<String, String> orderByColumn, HashMap<String, String> searchByColumns);

    Accounts getAccount(String userName);
}