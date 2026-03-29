package com.founderlink.investmentservice.service;

import com.founderlink.investmentservice.entity.*;
import com.founderlink.investmentservice.messaging.InvestmentEventPublisher;
import com.founderlink.investmentservice.repository.InvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentService {

    private final InvestmentRepository repo;
    private final InvestmentEventPublisher publisher;

    public Investment create(Investment inv) {
        Investment saved = repo.save(inv);
        publisher.publishInvestmentCreated(saved);
        return saved;
    }

    public Investment updateStatus(Long id, String status) {
        Investment inv = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Investment not found"));

        inv.setStatus(InvestmentStatus.valueOf(status));
        return repo.save(inv);
    }

    public List<Investment> getByStartup(Long startupId) {
        return repo.findByStartupId(startupId);
    }

    public List<Investment> getByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }
}