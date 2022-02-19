package kea.sem3.jwtdemo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
public class Reservation {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    LocalDate reservationDate;

    LocalDate rentalDate;

    @ManyToOne
    //@JoinColumn(name = "member_id", referencedColumnName = "username")
    Member reservedMember;

    @ManyToOne
    //@JoinColumn(name = "car_id", referencedColumnName = "id")
    Car reservedCar;


    public Reservation() {
    }

    public Reservation(LocalDate rentalDate, Car reservatedCar, Member reservedTo) {
        this.rentalDate = rentalDate;
        this.reservedCar = reservatedCar;
        this.reservedMember = reservedTo;
        reservedCar.addReservation(this);
        reservedMember.addReservation(this);
    }
}
