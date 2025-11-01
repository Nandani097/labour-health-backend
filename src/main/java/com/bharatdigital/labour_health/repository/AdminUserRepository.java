package com.bharatdigital.labour_health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bharatdigital.labour_health.model.AdminUser;
import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findByUsername(String username);
}
