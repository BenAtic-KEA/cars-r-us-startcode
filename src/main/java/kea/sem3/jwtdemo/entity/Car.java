package kea.sem3.jwtdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    int id;
    public Car() {
    }
}
