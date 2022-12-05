package io.cartin.backend.repository;

import io.cartin.backend.models.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {



}
