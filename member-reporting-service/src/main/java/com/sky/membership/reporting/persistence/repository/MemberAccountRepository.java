package com.sky.membership.reporting.persistence.repository;

import com.sky.membership.reporting.persistence.model.MemberAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberAccountRepository extends JpaRepository<MemberAccount, Long> {

    @Query("SELECT m FROM MemberAccount m WHERE m.status = 'ACTIVE'")
    List<MemberAccount> findAllActiveMembers();
    @Query("SELECT COUNT(m) FROM MemberAccount m WHERE m.status = 'ACTIVE'")
    Long countActiveMembers();

}
