package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

@Mock
    MemberRepository memberRepository;

MemberService memberService;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        memberService = new MemberService(memberRepository);
    }


    @Test
    void testGetMembers() {
        Mockito.when(memberRepository.findAll()).thenReturn(List.of(
                new Member("test1","test1@mail.dk","test111","testName1","testLastN1","street1","city1",1111),
                new Member("test2","test2@mail.dk","test222","testName2","testLastN2","street2","city2",2222)));

        assertEquals(2,memberService.getMembers(false).size());
    }

    @Test
    void testGetMember() {
        Member member = new Member("test1111","test1@mail.dk","test111","testName1","testLastN1","street1","city1",1111);
        member.setUsername("test12");
        Mockito.when(memberRepository.findById(member.getUsername())).thenReturn(Optional.of(member));
        MemberResponse membRes = memberService.getMember(member.getUsername(),false);
        assertEquals(member.getUsername(), membRes.getUsername());
    }


    @Test
    void testAddMember() {
        Member member = new Member("test1111","test1@mail.dk","test111","testName1","testLastN1","street1","city1",1111);
        Mockito.when(memberRepository.save(any(Member.class))).thenReturn(member);
        MemberResponse membRes = memberService.addMember(new MemberRequest(
                member.getUsername(),
                member.getEmail(),
                member.getPassword(),
                member.getFirstName(),
                member.getLastName(),
                member.getStreet(),
                member.getCity(),
                member.getZip()));
        assertEquals("test1111",membRes.getUsername());
    }

    @Test
    void testUpdateEmail() {
        Member member = new Member("test1111","test1@mail.dk","test111","testName1","testLastN1","street1","city1",1111);

        Mockito.when(memberRepository.save(any(Member.class))).thenReturn(member);
        Mockito.when(memberRepository.findById(any(String.class))).thenReturn(Optional.of(member));

        assertEquals("newTest1@mail.dk",memberService.updateEmail("test1111","newTest1@mail.dk").getEmail());
        assertEquals("newTest1@mail.dk",memberRepository.findById("test1111").get().getEmail());

    }

    @Test
    void testEditMember() {
        Member member = new Member("test1111","test1@mail.dk","test111","testName1","testLastN1","street1","city1",1111);
        MemberRequest membReq = new MemberRequest("test1111","test2@mail.dk","test222","testName2","testLastN2","street2","city2",2222);
        Mockito.when(memberRepository.save(any(Member.class))).thenReturn(member);
        Mockito.when(memberRepository.findById("test1111")).thenReturn(Optional.of(member));

        MemberResponse membRes = memberService.editMember("test1111",membReq);

        assertEquals(membReq.getEmail(),membRes.getEmail());
        assertEquals(membReq.getFirstName(),membRes.getFirstName());
        assertEquals(membReq.getLastName(),membRes.getLastName());
        assertEquals(membReq.getStreet(),membRes.getStreet());
        assertEquals(membReq.getCity(),membRes.getCity());
        assertEquals(membReq.getZip(),membRes.getZip());
    }

    @Test
    void testDeleteMember() {
    }
}