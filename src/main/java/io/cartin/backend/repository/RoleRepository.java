package io.cartin.backend.repository;

import io.cartin.backend.models.Role;
import io.cartin.backend.models.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType (RoleType roleType);
}
