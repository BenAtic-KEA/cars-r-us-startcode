package kea.sem3.jwtdemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Reservation {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    LocalDateTime reservationDate;

    LocalDateTime rentalDate;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "username")
    Member member = new Member();

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    Car car = new Car();


    public Reservation() {
    }

    public Reservation(int id, LocalDateTime reservationDate, LocalDateTime rentalDate) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.rentalDate = rentalDate;
    }
}
