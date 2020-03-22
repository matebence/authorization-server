package com.blesk.authorizationserver.Repository.Logins;

import com.blesk.authorizationserver.Model.Logins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginJpaRepository extends JpaRepository<Logins, Long> {
}
