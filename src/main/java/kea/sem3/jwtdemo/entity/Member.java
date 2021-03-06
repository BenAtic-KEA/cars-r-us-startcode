package kea.sem3.jwtdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kea.sem3.jwtdemo.dto.MemberRequest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@DiscriminatorValue("MEMBER")
public class Member extends BaseUser {

    @Column(name = "fornavn")
    private String firstName;
    @Column(name = "efternavn")
    private String lastName;
    @Column(name = "vejnavn")
    private String street;
    @Column(name = "bynavn")
    private String city;
    @Column(name = "postnummer")
    private int zip;
    private boolean approved;
    private int ranking;

    @JsonIgnore
    @OneToMany(mappedBy = "reservedMember", fetch = FetchType.EAGER)
    private Set<Reservation> reservations = new HashSet<>();


    public Member() {}

    public Member(String username, String email, String password, String firstName, String lastName, String street, String city, int zip) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.approved = false;
        this.ranking = 0;

    }

    public Member(MemberRequest body) {
        super(body.getUsername(),body.getEmail(),body.getPassword());

        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.street = body.getStreet();
        this.city = body.getCity();
        this.zip = body.getZip();
        this.approved = false;
        this.ranking = 0;
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    public boolean isApproved() {
        return approved;
    }

    public int getRanking() {
        return ranking;
    }

}
