package com.sky.services;

import com.sky.model.Member;
import com.sky.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Autowired
    @Output(Channels.CANCEL_OUTPUT_CHANNEL)
    private MessageChannel cancelChannel;

    @Autowired
    @Output(Channels.RETAIN_OUTPUT_CHANNEL)
    private MessageChannel retainChannel;


    public MemberService(MemberRepository members) {
        this.members = members;
    }

    public Member addMember(Member member) {

        Member newMember = members.save(member);

        enrollChannel.send(createMessage(member));
        return newMember;

    }

    public void deleteMember(long id) {
        Member member = members.findOne(id);
        if (member != null) {
            if (member.getBouquet().equals(Member.Bouquet.PREMIUM)) {
                retainChannel.send(createMessage(member));

            } else {
                cancelChannel.send(new GenericMessage<Member>(member));
                members.delete(id);
            }
        }
    }

    public boolean isValidMemberId(long id) {
        if (members.findOne(id) != null)
            return true;
        else return false;
    }

    public List<Member> getAllMembers() {
        return members.findAll();
    }

    public Member getMember(Long id) {
        return members.findOne(id);
    }

    private GenericMessage<Member> createMessage(Member member) {
        Map<String, Object> headers = new HashMap<>();
//        headers.put(MessageHeaders.CONTENT_TYPE, "application/json");
        GenericMessage<Member> message = new GenericMessage<Member>(member, headers);
//        MessageHeaders headers = message.getHeaders();
//        headers.put(MessageHeaders.CONTENT_TYPE, "application/json");
        return message;

    }

    public interface Channels {
        String ENROLL_OUTPUT_CHANNEL = "enroll";
        String CANCEL_OUTPUT_CHANNEL = "cancel";
        String RETAIN_OUTPUT_CHANNEL = "retain";


        @Output(ENROLL_OUTPUT_CHANNEL)
        MessageChannel enrollOutputChannel();

        @Output(CANCEL_OUTPUT_CHANNEL)
        MessageChannel cancelOutputChannel();

        @Output(RETAIN_OUTPUT_CHANNEL)
        MessageChannel retainOutputChannel();


    }


}
