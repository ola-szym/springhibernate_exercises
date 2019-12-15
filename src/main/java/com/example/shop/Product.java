package com.example.shop;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime dateAdded;
    private String name;

    private int quantity;
    private float price;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ShoppingCart shoppingCart;

    public Product(Long id, LocalDateTime dateAdded, String name, int quantity, float price) {
        this.id = id;
        this.dateAdded = dateAdded;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ShoppingCart getShoppingCart() { return shoppingCart; }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
