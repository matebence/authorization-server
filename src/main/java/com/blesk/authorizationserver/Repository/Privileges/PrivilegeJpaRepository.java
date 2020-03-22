package com.blesk.authorizationserver.Repository.Privileges;

import com.blesk.authorizationserver.Model.Privileges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeJpaRepository extends JpaRepository<Privileges, Long> {
}
