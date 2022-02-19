package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getReservations(){
        return reservationService.getReservations();
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation body){
        return reservationService.addReservation(body);

    }

}
