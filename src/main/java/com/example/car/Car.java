package com.example.car;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long id;

    private String make;
    private String color;

    public Car(String make, String color) {
        this.make = make;
        this.color = color;
    }

    public String getMake() {
        return make;
    }

    public String getColor() {
        return color;
    }

}
