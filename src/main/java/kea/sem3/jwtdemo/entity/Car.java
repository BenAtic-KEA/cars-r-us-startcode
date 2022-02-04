package kea.sem3.jwtdemo.entity;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;
    @Column(name = "Dagspris")
    private double pricePrDay;

    public Car() {}

    public Car(String brand, String model, double pricePrDay) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPricePrDay() {
        return pricePrDay;
    }
}
