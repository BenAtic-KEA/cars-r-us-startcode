package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

   MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
