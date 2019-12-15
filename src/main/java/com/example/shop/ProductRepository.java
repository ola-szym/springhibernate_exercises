package com.example.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName (String name);
    Set<Product> findByShoppingCartAndDateAddedBetween(ShoppingCart shoppingCart, LocalDateTime startTime, LocalDateTime endTime);
}
