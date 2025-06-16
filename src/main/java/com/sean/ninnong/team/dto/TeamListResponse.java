package com.sean.ninnong.team.dto;

import com.sean.ninnong.team.Team;

public record TeamListResponse(Long id, String name, String region, String logo, String description) {
    public static TeamListResponse from(Team team) {
        return team.toListResponse();
    }
}
