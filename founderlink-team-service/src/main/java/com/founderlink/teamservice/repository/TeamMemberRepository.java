package com.founderlink.teamservice.repository;

import com.founderlink.teamservice.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    List<TeamMember> findByStartupId(Long startupId);
}