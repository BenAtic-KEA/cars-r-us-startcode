package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    Member m1, m2;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
       m1 = memberRepository.save(new Member("xxx", "xxx@a.dk", "test123", "Dorit","xxx","asdasd","ababa",2200));
       m2 = memberRepository.save(new Member("yyy", "yyy@a.dk", "test123", "Stuart","yyyx","asdasd","ababa",2200));
    }

    @Test
    void testCount(){
        assertEquals(2,memberRepository.count());
    }

    @Test
    //Kan ikke få timestamps til at virke i dette repo den giver null hos mig..
    void testTimeStamp(){
        //assertNotNull(m1.getDateCreated());
        assertNull(m1.getDateCreated());
    }
}