package com.founderlink.startupservice.service;

import com.founderlink.startupservice.entity.*;
import com.founderlink.startupservice.messaging.StartupEventPublisher;
import com.founderlink.startupservice.repository.StartupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StartupService {

    private final StartupRepository repo;
    private final StartupEventPublisher publisher;

    public Startup create(Startup s) {
        Startup saved = repo.save(s);
        publisher.publishStartupCreated(saved);
        return saved;
    }

    public Startup getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Startup not found"));
    }

    public List<Startup> search(String industry, String stage) {
        return repo.search(industry, stage);
    }

    public Startup update(Long id, Startup updated) {
        Startup s = getById(id);

        if (updated.getName() != null) s.setName(updated.getName());
        if (updated.getDescription() != null) s.setDescription(updated.getDescription());
        if (updated.getFundingGoal() != null) s.setFundingGoal(updated.getFundingGoal());
        if (updated.getStage() != null) s.setStage(updated.getStage());

        return repo.save(s);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Startup approve(Long id) {
        Startup s = getById(id);
        s.setApprovalStatus(ApprovalStatus.APPROVED);
        return repo.save(s);
    }
}