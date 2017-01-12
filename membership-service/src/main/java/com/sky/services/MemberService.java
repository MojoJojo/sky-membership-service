package com.sky.services;

import com.sky.model.Member;
import com.sky.repositories.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sanketsharma on 1/12/17.
 */
@Component
public class MemberService {

    MemberRepository members;

    public MemberService(MemberRepository members) {
        this.members = members;
    }

    public Member addMember(Member member) {
        return members.save(member);

    }
    public void deleteMember(long id) {
         members.delete(id);
    }

    public boolean isValidMemberId(long id) {
        if(members.findOne(id)!=null)
            return true;
        else return false;
    }

    public List<Member> getAllMembers() {
        return members.findAll();
    }

    public Member getMember(Long id) {
        return members.findOne(id);
    }


}
