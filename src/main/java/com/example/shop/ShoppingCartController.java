package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    private Map<Long, ShoppingCart> shoppingCartDatabase = new HashMap<>();

    //pobranie wszystkich koszyków
    @RequestMapping(value = "/shoppingCarts", method = RequestMethod.GET)
    public ResponseEntity<Collection<ShoppingCart>> getShoppingCarts(){
        return ResponseEntity.ok(shoppingCartDatabase.values());
    }

    //dodawanie nowego koszyka
    @RequestMapping(value = "/shoppingCarts", method = RequestMethod.POST)
    public ResponseEntity<?> addShoppingCart(@RequestBody ShoppingCart shoppingCart){
        long id = shoppingCartDatabase.size()+1;
        shoppingCartDatabase.put(id, shoppingCart);
        return ResponseEntity.ok().build();
    }

    //usuwanie koszyka po id
    @RequestMapping(value = "/shoppingCarts/{id}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable long id){
        ShoppingCart foundShoppingCart = shoppingCartDatabase.get(id);
        shoppingCartDatabase.remove(foundShoppingCart);
    }

    //dodawanie produktu do koszyka
    @RequestMapping(value = "/shoppingCarts", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@PathVariable long shoppingCartId, @RequestBody Product product){
        shoppingCartService.addProduct(shoppingCartId,product);
        return ResponseEntity.ok().build();
    }

    //usuwanie produktu z koszyka
    @RequestMapping(value = "/shoppingCarts", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable long shoppingCartId, @RequestBody long productId){
        shoppingCartService.removeProduct(shoppingCartId,productId);
        return ResponseEntity.ok().build();
    }


    //wyczyszczenie całego koszyka
    @RequestMapping(value = "/shoppingCarts", method = RequestMethod.DELETE)
    public void emptyShoppingCart(@PathVariable long shoppingCartId){
        shoppingCartService.emptyShoppingCart(shoppingCartId);
    }

}
