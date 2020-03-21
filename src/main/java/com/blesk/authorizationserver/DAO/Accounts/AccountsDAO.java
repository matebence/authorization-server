package com.blesk.authorizationserver.DAO.Accounts;

import com.blesk.authorizationserver.DAO.DAO;
import com.blesk.authorizationserver.Model.Accounts;

import java.util.HashMap;
import java.util.List;

public interface AccountsDAO extends DAO<Accounts> {

    Accounts getAccountInformations(String userName);

    List<Accounts> searchBy(HashMap<String, HashMap<String, String>> criterias);
}
