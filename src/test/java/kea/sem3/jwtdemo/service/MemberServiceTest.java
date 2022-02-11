package kea.sem3.jwtdemo.service;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

}