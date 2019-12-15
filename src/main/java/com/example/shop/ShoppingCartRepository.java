package com.example.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
