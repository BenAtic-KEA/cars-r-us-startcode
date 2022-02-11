package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    static String m1,m2;
    @BeforeEach
    void setUp() {
       m1 = memberRepository.save(new Member("xxx", "xxx@a.dk", "test123", "Dorit","xxx","asdasd","ababa",2200)).getUsername();
       m2 = memberRepository.save(new Member("yyy", "yyy@a.dk", "test123", "Stuart","yyyx","asdasd","ababa",2200)).getUsername();
    }

    @Test
    void testCount(){
        assertEquals(2,memberRepository.count());
    }

    @Test
    //Kan ikke få timestamps til at virke i dette repo den giver null hos mig..
    void testTimeStamp(){
        LocalDateTime m3 = memberRepository.save(new Member("xxx3", "xxx3@a.dk", "test1234", "Dorit3","xxx3","asdasd3","ababa3",2200)).getDateCreated();
        LocalDateTime created = memberRepository.findById("xxx3").orElseThrow(()-> new Client4xxException("Username doesnt exist")).getDateCreated();
        assertEquals(m3,created);
    }
}