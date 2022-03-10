package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/cars")
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * USER view, some data are hidden
     * @return list of all cars in DB
     */
    @GetMapping
    public List<CarResponse> getCars(){
        return carService.getCars(false);
    }

    /**
     * Admin view
     * @return view with all info
     */
    @GetMapping("/admin")
    public List<CarResponse> getFullCarList(){
        return carService.getCars(true);
    }

    /**
     * USER view, some data are hidden
     * @param id carId
     * @return Returns a Specific car
     */
    @GetMapping("/{id}")
    public CarResponse getCar(@PathVariable int id){
        return carService.getCar(id,false);
    }


    /**
     * ADMIN function, able to add new cars
     * @param body CarRequest Object
     * @return the added car as a CarResponse
     */
    @PostMapping
    public CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    /**
     * ADMIN function, update Car
     * @param body updated object
     * @param id car to update
     * @return Updated car object
     */

    @PutMapping("/{id}")
    public CarResponse editCar(@RequestBody CarRequest body, @PathVariable int id) {
        return carService.editCar(body,id);}

    /**
     * ADMIN function, delete Car
     * @param id car to delete
     */
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id){
        carService.deleteCar(id);
    }


    @PatchMapping("/{id}/{pricePrDay}")
    public CarResponse editPrice(@PathVariable double pricePrDay, @PathVariable int id){
        return carService.updatePrice(id,pricePrDay);
    }
}

