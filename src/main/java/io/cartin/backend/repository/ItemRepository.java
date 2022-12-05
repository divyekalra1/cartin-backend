package io.cartin.backend.repository;

import io.cartin.backend.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findById(Long id);
    boolean existsById(Long id);
}