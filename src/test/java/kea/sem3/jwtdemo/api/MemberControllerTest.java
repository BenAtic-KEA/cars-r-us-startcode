package kea.sem3.jwtdemo.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.service.MemberService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.security.RolesAllowed;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MemberControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvC;

    @Autowired
    ObjectMapper objectMapper;

    static String memberId1, memberId2;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
        memberId1 = memberRepository.save(new Member("xxx", "xxx@a.dk", "test111", "Dorit","x1xx2","asdasdxx","ababaxx",2200)).getUsername();
        memberId2 = memberRepository.save(new Member("yyy", "yyy@a.dk", "test222", "Jarl","yyy","asdasdyy","ababayy",2250)).getUsername();
    }

    @Test
    @WithMockUser(username = "kurt", password = "xxxxxx", authorities = "ADMIN")
    void getMembers() throws Exception {
        String username = "$[?(@.username == '%s')]";
        mockMvC.perform(MockMvcRequestBuilders.get("/api/members")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath(username,"xxx").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(username,"yyy").exists());
    }

    @Test
    @WithMockUser(username = "kurt", password = "xxxxxx", authorities = "ADMIN")
    void testGetMember() throws Exception {
        mockMvC.perform(MockMvcRequestBuilders
                        .get("/api/members/" + memberId1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(memberId1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("xxx"));
    }

    @Test
    @WithMockUser(username = "kurt", password = "xxxxxx", authorities = "ADMIN")
    void testAddMember() throws Exception {
        MemberRequest memberRequest = new MemberRequest("test1","test1@mail.dk","test1234","Mark","Hansen","testvej1","by1",2200);

        mockMvC.perform(MockMvcRequestBuilders.post("/api/members")
                .contentType("application/json")
                .accept("application/json")
                .content(objectMapper.writeValueAsString(memberRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").exists());
        assertEquals("test1",memberRepository.findById("test1").get().getUsername());
    }


}