package com.blesk.authorizationserver.Repository.Roles;

import com.blesk.authorizationserver.Model.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleCrudRepository extends CrudRepository<Roles, Long> {
}