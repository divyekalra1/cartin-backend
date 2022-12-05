package io.cartin.backend.repository;

import io.cartin.backend.models.CartItem;
import io.cartin.backend.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCart_Id(Long id);
    boolean existsByCart_Id(Long id);

}
