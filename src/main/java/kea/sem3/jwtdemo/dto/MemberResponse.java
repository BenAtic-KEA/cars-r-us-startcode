package kea.sem3.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private int zip;
    private Boolean approved;
    private Integer ranking;
    private LocalDateTime created;
    private LocalDateTime dateEdited;


public MemberResponse(Member member, boolean includeAll){
    this.username = member.getUsername();
    this.email = member.getEmail();
    this.firstName = member.getFirstName();
    this.lastName = member.getLastName();
    this.street = member.getStreet();
    this.city = member.getCity();
    this.zip = member.getZip();

    if(includeAll){
        this.created = member.getDateCreated();
        this.dateEdited = member.getDateEdited();
        this.approved = member.isApproved();
        this.ranking = member.getRanking();
    }
}

    public static List<MemberResponse> getMembersFromEntities(List<Member> members, boolean all){
        return members.stream().map(member -> new MemberResponse(member,all)).collect(Collectors.toList());
    }



}
