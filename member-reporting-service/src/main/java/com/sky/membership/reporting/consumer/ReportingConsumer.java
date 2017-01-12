package com.sky.membership.reporting.consumer;

import com.sky.membership.reporting.model.Member;
import com.sky.membership.reporting.persistence.model.MemberAccount;
import com.sky.membership.reporting.persistence.repository.MemberAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@EnableBinding(Channels.class)
public class ReportingConsumer {

    private final MemberAccountRepository memberAccountRepository;

    public ReportingConsumer(MemberAccountRepository memberAccountRepository) {
        this.memberAccountRepository = memberAccountRepository;
    }

    @StreamListener(Channels.ENROL_INPUT)
    public void enrolMember(Member member) {
        log.info("Enrolment received: {}", member.toString());

        MemberAccount account = MemberAccount.builder()
                .id(member.getId())
                .bouquet(member.getBouquet().toString())
                .name(member.getName())
                .status(MemberAccount.AccountStatus.ACTIVE)
                .build();

        memberAccountRepository.save(account);
    }

    @StreamListener(Channels.CANCEL_INPUT)
    public void cancelMember(Member member) {
        log.info("Cancellation received: {}", member.toString());

        MemberAccount account = memberAccountRepository.findOne(member.getId());
        if (account != null) {
            account.setStatus(MemberAccount.AccountStatus.CANCELLED);
            memberAccountRepository.save(account);
        } else {
           log.error("Could not cancel member account with id {}. Not found.", member.getId());
        }
    }
}
