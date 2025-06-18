package com.sean.ninnong.team.dto;

import com.sean.ninnong.team.Team;

public record TeamResponse(Long id, String name, String region, String logo, String description) {
    public static TeamResponse from(Team team) {
        return team.toResponse();
    }
}
