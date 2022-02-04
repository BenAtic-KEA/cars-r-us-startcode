package kea.sem3.jwtdemo.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MEMBER")
public class Member extends BaseUser {

    String firstName;
    /*
    String lastName;
    String street;
    String city;
    int zip;
    boolean approved;
    int ranking;
    */
    public Member() {}

    public Member(String username, String email, String password, String firstName) {
        super(username, email, password);
        this.firstName = firstName;
    }
}
