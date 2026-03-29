package com.founderlink.startupservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "startups")
@Data
public class Startup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String industry;

    private String problemStatement;

    private String solution;

    private BigDecimal fundingGoal;

    private String location;

    @Enumerated(EnumType.STRING)
    private StartupStage stage;

    private Long founderId;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();
}