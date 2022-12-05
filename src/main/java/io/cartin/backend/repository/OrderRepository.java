package io.cartin.backend.repository;

import io.cartin.backend.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser_Id(Long id);
    boolean existsByUser_Id(Long id);

}
