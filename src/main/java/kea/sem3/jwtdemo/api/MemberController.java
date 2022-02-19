package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * USER view, hiding member data
     * @return List of all members in the DB
     */
    @GetMapping
    public List<MemberResponse> getMembers(){
        return memberService.getMembers(false);
    }

    /**
     * USER view, hiding member data.
     * @param username
     * @return MemberResponse
     */
    @GetMapping("/{username}")
    public MemberResponse getMember(@PathVariable String username){

        return memberService.getMember(username,false);
    }

    /**
     * ANY USER is able to make a account
     * @param body
     * @return the created object for confirmation
     */
    @PostMapping
    public MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }

    @PutMapping("/{username}")
    public MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String username){
        return memberService.editMember(username, body);

    }

    @PatchMapping("/{username}/{newMail}")
    public MemberResponse editEmail(@PathVariable String username, @PathVariable String newMail){
        return memberService.updateEmail(username,newMail);
    }

    @DeleteMapping("/{username}")
    public void deleteMember(@PathVariable String username){
        memberService.deleteMember(username);
    }




}
