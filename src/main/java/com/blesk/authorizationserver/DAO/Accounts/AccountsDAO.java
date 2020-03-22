package com.blesk.authorizationserver.DAO.Accounts;

import com.blesk.authorizationserver.DAO.DAO;
import com.blesk.authorizationserver.Model.Accounts;

public interface AccountsDAO extends DAO<Accounts> {

    Accounts getAccountInformations(String userName);
}
