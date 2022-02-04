package kea.sem3.jwtdemo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
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

    @Column(name="oprettet")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "rettet")
    @UpdateTimestamp
    private LocalDateTime dateEdited;


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

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateEdited(LocalDateTime dateEdited) {
        this.dateEdited = dateEdited;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateEdited() {
        return dateEdited;
    }
}
