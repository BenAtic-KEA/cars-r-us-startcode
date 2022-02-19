package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(boolean includeAll){
        return CarResponse.getCarsFromEntities(carRepository.findAll(),includeAll);
    }

    public CarResponse getCar(int id,boolean all){
        return new CarResponse(carRepository.findById(id).orElseThrow(()-> new Client4xxException("no car with this ID exist")),all);
    }

    public CarResponse addCar(CarRequest body){
        Car carNew = carRepository.save(new Car(body));
        return new CarResponse(carNew,true);
    }
    //Admin da de får returneret alle data om redigeret objekt
    public CarResponse editCar(CarRequest body,int carId){
        carRepository.findById(carId).orElseThrow(()-> new Client4xxException(("No car with provided ID")));
        Car carToEdit = new Car(body);
        carToEdit.setId(carId);
        return new CarResponse(carRepository.save(carToEdit),true);
    }
    //Admin
    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    //Service method for PATCH
    public CarResponse updatePrice(int carId,double newPricePrDay){
        Car car = carRepository.findById(carId).orElseThrow(()-> new Client4xxException(("No car with provided ID")));
        car.setPricePrDay(newPricePrDay);
        return new CarResponse((carRepository.save(car)),true);
    }


}
