package com.founderlink.teamservice.service;

import com.founderlink.teamservice.dto.InviteRequest;
import com.founderlink.teamservice.entity.*;
import com.founderlink.teamservice.messaging.TeamEventPublisher;
import com.founderlink.teamservice.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamMemberRepository repo;
    private final TeamEventPublisher publisher;

    public TeamMember invite(InviteRequest req) {
        TeamMember member = new TeamMember();
        member.setStartupId(req.getStartupId());
        member.setUserId(req.getUserId());
        member.setRole(req.getRole());

        TeamMember saved = repo.save(member);

        publisher.publishTeamInvite(saved);
        return saved;
    }

    public TeamMember acceptInvite(Long memberId, Long userId) {

        TeamMember member = repo.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Invite not found"));

        if (!member.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        member.setStatus(InviteStatus.ACCEPTED);
        member.setJoinedAt(LocalDateTime.now());

        return repo.save(member);
    }

    public List<TeamMember> getTeamByStartup(Long startupId) {
        return repo.findByStartupId(startupId);
    }
}