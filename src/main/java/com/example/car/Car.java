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
    private Color color;

    public Car(String make, Color color) {
        this.make = make;
        this.color = color;
    }

    public Long getId() { return id; }

    public String getMake() {
        return make;
    }

    public Color getColor() {
        return color;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
