package io.cartin.backend.repository;

import io.cartin.backend.models.Cart;
import io.cartin.backend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser_Id(Long id);
    boolean existsByUser_Id(Long id);
}
