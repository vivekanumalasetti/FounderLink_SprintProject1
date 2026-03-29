package com.founderlink.investmentservice.controller;

import com.founderlink.investmentservice.entity.Investment;
import com.founderlink.investmentservice.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/investments")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService service;

    @PostMapping
    public ResponseEntity<Investment> create(
            @RequestBody Investment investment,
            @RequestHeader("X-User-Id") Long investorId) {

        investment.setInvestorId(investorId);
        return ResponseEntity.ok(service.create(investment));
    }

    @GetMapping("/startup/{startupId}")
    public ResponseEntity<List<Investment>> byStartup(@PathVariable Long startupId) {
        return ResponseEntity.ok(service.getByStartup(startupId));
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<Investment>> byInvestor(@PathVariable Long investorId) {
        return ResponseEntity.ok(service.getByInvestor(investorId));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Investment> approve(@PathVariable Long id) {
        return ResponseEntity.ok(service.updateStatus(id, "APPROVED"));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Investment> reject(@PathVariable Long id) {
        return ResponseEntity.ok(service.updateStatus(id, "REJECTED"));
    }
}