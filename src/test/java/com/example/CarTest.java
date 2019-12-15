package com.example;

import com.example.car.Car;
import com.example.car.CarRepository;
import com.example.car.CarService;
import com.example.car.Color;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarTest {

    @Autowired
    CarRepository carRepository;

    @Autowired
    EntityManager em;


//    @Test
//    @Transactional
//    public void shouldFindCars_byColor() {
//        //given
//        Car car1 = new Car("Toyota", Color.BLACK);
//        Car car2 = new Car("Opel", Color.BLUE);
//        Car car3 = new Car("Skoda", Color.BLACK);
//        carRepository.save(car1);
//        carRepository.save(car2);
//        carRepository.save(car3);
//
//        //when
//        List<Car> foundCars = carRepository.findCarByColor(Color.BLACK);
//
//        //then
//        Assert.assertEquals(2, foundCars.size());
//    }
//
//    @Test
//    @Transactional
//    public void shouldFindCars_byColors() {
//        //given
//        Car car1 = new Car("Toyota", Color.BLACK);
//        Car car2 = new Car("Opel", Color.BLUE);
//        Car car3 = new Car("Skoda", Color.BLACK);
//        carRepository.save(car1);
//        carRepository.save(car2);
//        carRepository.save(car3);
//
//        List<Color> colors = new ArrayList<>();
//        colors.add(Color.BLACK);
//        colors.add(Color.BLUE);
//
//        //when
//        List<Car> foundCars =
//
//        //then
//        Assert.assertEquals(3,foundCars.size());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void shouldFindCars_byMake() {
//        //given
//        Car car1 = new Car("Toyota", Color.BLACK);
//        Car car2 = new Car("Toyota", Color.BLUE);
//        Car car3 = new Car("Skoda", Color.BLACK);
//        carRepository.save(car1);
//        carRepository.save(car2);
//        carRepository.save(car3);
//
//        //when
//        List<Car> foundCars = carRepository.findCarByMake("Toyota");
//
//        //then
//        Assert.assertEquals(2, foundCars.size());
//    }

    @Test
    @Transactional
    public void shouldDeleteCars_byColor() {
        //given
        Car car1 = new Car("Toyota", Color.BLACK);
        Car car2 = new Car("Toyota", Color.BLUE);
        Car car3 = new Car("Skoda", Color.BLACK);
        carRepository.save(car1);
        em.flush();
        carRepository.save(car2);
        em.flush();
        carRepository.save(car3);
        em.flush();

        //when
        CarService carService = new CarService();
        carService.deleteCarsByColor(Color.BLACK);


        List<Car> foundCars = carRepository.findAll();

        //then

    }


}