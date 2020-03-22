package com.blesk.authorizationserver.Repository.Accounts;

import com.blesk.authorizationserver.Model.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountCrudRepository extends CrudRepository<Accounts, Long> {
}
