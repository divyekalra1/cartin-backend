package io.cartin.backend.repository;

import io.cartin.backend.models.Order;
import io.cartin.backend.models.OrderStatus;
import io.cartin.backend.models.OrderStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    Optional<OrderStatus> findByStatusType(OrderStatusType statusType);
    boolean existsByStatusType(OrderStatusType statusType);


}
