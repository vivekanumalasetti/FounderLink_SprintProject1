package com.founderlink.teamservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "team_members")
@Data
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long startupId;
    private Long userId;

    private String role;

    @Enumerated(EnumType.STRING)
    private InviteStatus status = InviteStatus.PENDING;

    private LocalDateTime joinedAt;
}