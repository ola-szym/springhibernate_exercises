package com.example.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired // wstrzykiwanie moze byc na polu albo na konstruktorze
    private CarRepository carRepository;

//    @Autowired // wstrzykiwanie moze byc na polu albo na konstruktorze
//    public CarService(CarRepository carRepository) {
//        this.carRepository = carRepository;
//    }

    // w tej metodzie wszystkie marki i kolory sa unikalne


    public List<Car> fetchAllCars() {
        return new ArrayList<>();
    }

    @Transactional // bardzo zla nazwa!
    public List<Car> createCars2(List<String> makes, List<String> colors) {
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < makes.size(); i++) {
            Car car = createCar(makes.get(i), colors.get(i));
            cars.add(car);
        }

        return cars;
    }

    @Transactional
    public List<Car> createCars(List<String> makes, List<String> colors) {
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < makes.size(); i++) {
            Car car = new Car(makes.get(i), colors.get(i));
            cars.add(car);
        }

        return carRepository.saveAll(cars);
    }

    // utworz nowy samochod tylko wtedy jesli nie istnieje juz taka reprezentacja w bazie
    // 1. sprawdzamy czy taki samochod juz istnieje
    // a) znalazl samochod - wynik Car
    // b) nie znalazl samochodu - wynik null

    @Transactional
    public Car createCar(String make, String color) {
        Optional<Car> foundCar = carRepository.findCarByMakeAndColor(make, color);
        if (!foundCar.isPresent()) {
            Car car = new Car(make, color);
            return carRepository.save(car);
        } else {
            throw new IllegalArgumentException("Taki samochód (" + make + "," + color + ") już istnieje");
        }
    }

}
