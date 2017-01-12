package com.sky.membership.reporting.schedule;

import com.sky.membership.reporting.persistence.model.MemberAccount;
import com.sky.membership.reporting.persistence.repository.MemberAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ReportingScheduler {

    private final MemberAccountRepository repository;

    public ReportingScheduler(MemberAccountRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 10000)
    public void report() {
        Long activeCount = repository.countActiveMembers();

        log.info("You currently have [{}] ACTIVE members", activeCount);

        if (activeCount > 0) {
            log.info(">>>>> Outputting all current ACTIVE members <<<<<\n");
            repository.findAllActiveMembers().forEach(e -> log.info(e.toString()));
        }

    }

}
