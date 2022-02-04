package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.save(new Member("xxx", "xxx@a.dk", "test123", "Dorit","xxx","asdasd","ababa",2200));
        memberRepository.save(new Member("yyy", "yyy@a.dk", "test123", "Stuart","yyyx","asdasd","ababa",2200));
    }

    @Test
    void testCount(){
        assertEquals(2,memberRepository.count());
    }
}