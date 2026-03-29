package com.founderlink.teamservice.dto;

import lombok.Data;

@Data
public class InviteRequest {
    private Long startupId;
    private Long userId;
    private String role;
}