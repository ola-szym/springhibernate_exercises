package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public ShoppingCart createShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        return shoppingCartRepository.save(shoppingCart);
    }

    @Transactional
    public void addProduct(Long shoppingCartId, Product product) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (shoppingCart.isPresent()) {
            ShoppingCart existingShoppingCart = shoppingCart.get();
            existingShoppingCart.addProduct(product);
            shoppingCartRepository.save(existingShoppingCart);
        } else {
            throw new IllegalArgumentException("ShoppingCart: " + shoppingCartId + " does not exist");
        }
    }

    @Transactional
    public void addProducts(Long shoppingCartId, Set<Product> products) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (shoppingCart.isPresent()) {
            ShoppingCart existingShoppingCart = shoppingCart.get();
            existingShoppingCart.addProducts(products);
            shoppingCartRepository.save(existingShoppingCart);
        } else {
            throw new IllegalArgumentException("ShoppingCart: " + shoppingCartId + " does not exist");
        }
    }

    //4e
    @Transactional
    public Set<Product> findProducts(Long shoppingCartId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (shoppingCart.isPresent()) {
            Set<Product> foundProducts = shoppingCart.get().getProducts();
            return foundProducts;
        } else {
            throw new IllegalArgumentException("ShoppingCart: " + shoppingCartId + " does not exist");
        }
    }

    //4f
    @Transactional
    public float countShoppingCartPrice(Long shoppingCartId) {
        Optional<ShoppingCart> existingShoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (existingShoppingCart.isPresent()) {
            // wydzielic logike liczenia ceny do koszyka, np. calculatePrice()
            Set<Product> products = existingShoppingCart.get().getProducts();

            float shoppingCartPrice = 0;
            for (Product i : products) {
                shoppingCartPrice += i.getPrice();
            }
            return shoppingCartPrice;
        } else {
            throw new IllegalArgumentException("ShoppingCart: " + shoppingCartId + " does not exist");
        }
    }

    //4g
    @Transactional
    public int countShoppingCartQuantity(Long shoppingCartId) {
        Optional<ShoppingCart> existingShoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (existingShoppingCart.isPresent()) {
            Set<Product> products = existingShoppingCart.get().getProducts();

            int shoppingCartQuantity = 0;
            for (Product i : products) {
                shoppingCartQuantity += i.getQuantity();
            }
            return shoppingCartQuantity;
        } else {
            throw new IllegalArgumentException("ShoppingCart: " + shoppingCartId + " does not exist");
        }
    }

    //4h
    @Transactional
    public Set<Product> findProductsFromLastHour(Long shoppingCartId) {
        Optional<ShoppingCart> existingShoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (existingShoppingCart.isPresent()) {
            Set<Product> products = existingShoppingCart.get().getProducts();
            Set<Product> productsFromLastHour = new HashSet<>();

            for (Product product : products) {
                LocalDateTime startTime = LocalDateTime.now().minusHours(1);
                LocalDateTime endTime = LocalDateTime.now();

                if (product.getDateAdded().isAfter(startTime) && product.getDateAdded().isBefore(endTime)) {
                    productsFromLastHour.add(product);
                }
            }
            return productsFromLastHour;
        } else {
            throw new IllegalArgumentException("ShoppingCart: " + shoppingCartId + " does not exist");
        }
    }

    //4h druga metoda
    @Transactional
    public Set<Product> findProductsFromLastHour_2(Long shoppingCartId) {
        Optional<ShoppingCart> existingShoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (existingShoppingCart.isPresent()) {
            LocalDateTime startTime = LocalDateTime.now().minusHours(1);
            LocalDateTime endTime = LocalDateTime.now();
            Set<Product> productsFromLastHour = productRepository.findByShoppingCartAndDateAddedBetween(null/*todo*/, startTime, endTime);
            return productsFromLastHour;
        } else {
            throw new IllegalArgumentException("ShoppingCart: " + shoppingCartId + " does not exist");
        }
    }

    //4i
    @Transactional
    public void emptyShoppingCart(Long shoppingCartId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (shoppingCart.isPresent()) {
            Set<Product> products = shoppingCart.get().getProducts();
            products.clear();
            shoppingCartRepository.save(shoppingCart.get());
        } else {
            throw new IllegalArgumentException("ShoppingCart: " + shoppingCartId + " does not exist");
        }
    }


    //4j
    @Transactional
    public void removeProduct(Long shoppingCartId, Long productId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (shoppingCart.isPresent()) {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                ShoppingCart existingShoppingCart = shoppingCart.get();
                existingShoppingCart.removeProduct(product.get());
                shoppingCartRepository.save(existingShoppingCart);
            } else {
                throw new IllegalArgumentException("Product: " + product + "does not exists in shoppingCart");
            }
        } else {
            throw new IllegalArgumentException("ShoppingCart: " + shoppingCartId + " does not exist");
        }
    }

    //4k
    @Transactional
    public void addProduct2(Long shoppingCartId, Product product) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCartId);

        if (shoppingCart.isPresent()) {
            ShoppingCart existingShoppingCart = shoppingCart.get();
            Set<Product> products = existingShoppingCart.getProducts();

            for (Product element : products) {
                if (element.getName().equals(product.getName())) {
                    int productQuantity = element.getQuantity();
                    productQuantity += product.getQuantity();
                    product.setQuantity(productQuantity);
                    shoppingCartRepository.save(existingShoppingCart);
                } else {
                    existingShoppingCart.addProduct(product);
                    shoppingCartRepository.save(existingShoppingCart);
                }
            }
        } else {
            throw new IllegalArgumentException("ShoppingCart: " + shoppingCartId + " does not exist");
        }
    }


//    i) mozliwosc wyczyszczenia koszyka, czyli po wykonaniu tej operacji koszyk ma byc pusty
//    j) mozliwosc usuniecia danego produktu z koszyka (metoda removeProduct(Long shoppingCartId, Long productId) w serwisie)
//    k*) jesli przy dodawaniu produktu okaze sie, ze produkt o takiej nazwie juz istnieje w danym koszyku to chcemy tylko zwiekszyc jego ilosc o tyle ile wynosi ilosc w dodawanym produkcie

}
