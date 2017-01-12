package com.sky.services;

import com.sky.model.Member;
import com.sky.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sanketsharma on 1/12/17.
 */
@Component
@EnableBinding(MemberService.Channels.class)
public class MemberService {

    MemberRepository members;

    @Autowired
    @Output(Channels.ENROLL_OUTPUT_CHANNEL)
    private MessageChannel enrollChannel;

    public MemberService(MemberRepository members) {
        this.members = members;
    }

    public Member addMember(Member member) {

        Member newMember = members.save(member);
        enrollChannel.send(new GenericMessage<Member>(newMember));
        return newMember;

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

     public interface Channels {
        String ENROLL_OUTPUT_CHANNEL = "enroll";


        @Output(ENROLL_OUTPUT_CHANNEL)
        MessageChannel enrollOutputChannel();



    }


}
