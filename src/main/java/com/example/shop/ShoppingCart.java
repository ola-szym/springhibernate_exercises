package com.example.shop;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    public void addProduct(Product product){
        products.add(product);
    }

    public void addProducts(Set<Product> products){
        products.addAll(products);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }
}
