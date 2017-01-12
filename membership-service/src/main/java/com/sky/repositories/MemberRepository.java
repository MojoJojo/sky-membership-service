package com.sky.repositories;

import com.sky.model.Member;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by sanketsharma on 1/12/17.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {



}
