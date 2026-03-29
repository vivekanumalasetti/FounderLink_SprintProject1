package com.founderlink.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_profiles")
@Data
public class UserProfile {

    @Id
    private Long userId;

    private String name;
    private String email;
    private String bio;
    private String skills;
    private String experience;
    private String portfolioLinks;
    private String role;

    private LocalDateTime createdAt = LocalDateTime.now();
}