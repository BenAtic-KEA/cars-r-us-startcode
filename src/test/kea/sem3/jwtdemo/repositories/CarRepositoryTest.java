package kea.sem3.jwtdemo.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;


    @BeforeEach
    void setUp() {
    }
}