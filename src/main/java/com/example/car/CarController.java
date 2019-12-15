package com.example.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    private Map<Long, Car> carsDatabase = new HashMap<>();

    public CarController(){
        carsDatabase.put(1L,new Car("Opel", Color.BLACK));
        carsDatabase.put(2L,new Car("Toyota", Color.RED));
    }



    // endpoint, adres "../cars", metoda GET
    // kody http: 2xx, 3xx, 4xx, 5xx


    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public ResponseEntity<?> addCar(@RequestBody Car car){
        long id = carsDatabase.size()+1;
        carsDatabase.put(id, car);
        return ResponseEntity.ok().build();
    }


    //pobranie wszystkich samochodów
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ResponseEntity<Collection<Car>> getCars() {
        return ResponseEntity.ok(carsDatabase.values());
    }

    //pobranie samochodu po id
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public ResponseEntity<Car> getCar(@PathVariable long id){
        return ResponseEntity.ok(carsDatabase.get(id));
    }

    //usunięcie samochodu po id
    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable long id){
        System.out.println("XYZ");
        Car foundCar = carsDatabase.get(id);
        carsDatabase.remove(foundCar);
    }

    // usunięcie wszystkich samochodów
    @RequestMapping(value = "/cars", method = RequestMethod.DELETE)
    public void deleteCars(){
        carsDatabase.clear();
    }

}

// delete in spring boot rest