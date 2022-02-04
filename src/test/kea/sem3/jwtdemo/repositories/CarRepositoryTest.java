package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    static int carId1, carId2, carId3;

    @BeforeAll
    static void setUp(@Autowired CarRepository carRepository) {
    Car car1 = carRepository.save(new Car("Brand1","model1",123));
    carId1 = car1.getId();
    Car car2 = carRepository.save(new Car("Brand2","model2",111));
    carId2 = car2.getId();
    Car car3 = carRepository.save(new Car("Brand3","model4",123));
    carId3 = car3.getId();

    }

    @Test
    public void testCarCount(){
    assertEquals(3,carRepository.count());
    }

    @Test
    public void testFindById(){

        Car car1 = carRepository.findById(carId1).orElse(null);

        assert car1 != null;
        assertEquals("Brand1",car1.getBrand());


    }

    @Test
    public void testCarId(){
        assertNotEquals(0,carId1);
        assertNotEquals(carId1,carId2);
    }
}