package io.cartin.backend.repository;

import io.cartin.backend.models.CartinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface CartinUserRepository extends JpaRepository<CartinUser, Long> {
    Optional<CartinUser> findByEmail(String email);
    Boolean existsByEmail(String email);

}
