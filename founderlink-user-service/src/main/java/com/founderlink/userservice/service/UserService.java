package com.founderlink.userservice.service;

import com.founderlink.userservice.entity.UserProfile;
import com.founderlink.userservice.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserProfileRepository repo;

    public UserProfile createProfile(UserProfile p) {
        return repo.save(p);
    }

    public UserProfile getUser(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    public UserProfile updateUser(Long id, UserProfile updated) {
        UserProfile existing = getUser(id);

        if (updated.getBio() != null) existing.setBio(updated.getBio());
        if (updated.getSkills() != null) existing.setSkills(updated.getSkills());
        if (updated.getExperience() != null) existing.setExperience(updated.getExperience());
        if (updated.getPortfolioLinks() != null) existing.setPortfolioLinks(updated.getPortfolioLinks());

        return repo.save(existing);
    }

    public List<UserProfile> getAllUsers() {
        return repo.findAll();
    }
}