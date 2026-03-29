package com.founderlink.investmentservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "investments")
@Data
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long startupId;
    private Long investorId;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private InvestmentStatus status = InvestmentStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();
}