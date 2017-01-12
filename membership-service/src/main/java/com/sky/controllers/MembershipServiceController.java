package com.sky.controllers;

import com.sky.model.Member;
import com.sky.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sanketsharma on 1/12/17.
 */

@RestController
@RequestMapping(path = "/api/member/")
public class MembershipServiceController {
    @Autowired
    MemberService memberService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Member> members() {
        return memberService.getAllMembers();

    }
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Member member(@PathVariable Long id) {
        return memberService.getMember(id);

    }

    @RequestMapping(value = "enroll", method = RequestMethod.POST)
    public Member enroll(@RequestBody Member newMember) {
        return memberService.addMember(newMember);

    }

    @RequestMapping(value = "cancel/{id}", method = RequestMethod.DELETE)
    public boolean cancel(@PathVariable Long id) {
        if(memberService.isValidMemberId(id)) {
            memberService.deleteMember(id);
            return true;
        }
        else return false;

    }

}
