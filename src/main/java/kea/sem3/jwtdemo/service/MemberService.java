package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

   MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean all){
        return MemberResponse.getMembersFromEntities(memberRepository.findAll(),all);
    }

    public MemberResponse getMember(String username, boolean all) {
        return new MemberResponse(memberRepository.findById(username).orElseThrow(()-> new Client4xxException("username doesn't exist")),all);

    }
}
