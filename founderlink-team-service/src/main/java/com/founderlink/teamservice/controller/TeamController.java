package com.founderlink.teamservice.controller;

import com.founderlink.teamservice.dto.InviteRequest;
import com.founderlink.teamservice.entity.TeamMember;
import com.founderlink.teamservice.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/invite")
    public ResponseEntity<TeamMember> invite(@RequestBody InviteRequest req) {
        return ResponseEntity.ok(teamService.invite(req));
    }

    @PostMapping("/join/{memberId}")
    public ResponseEntity<TeamMember> join(
            @PathVariable Long memberId,
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(teamService.acceptInvite(memberId, userId));
    }

    @GetMapping("/startup/{startupId}")
    public ResponseEntity<List<TeamMember>> getTeam(@PathVariable Long startupId) {
        return ResponseEntity.ok(teamService.getTeamByStartup(startupId));
    }
}