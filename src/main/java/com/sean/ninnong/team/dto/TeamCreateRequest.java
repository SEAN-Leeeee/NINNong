package com.sean.ninnong.team.dto;

import com.sean.ninnong.teammember.dto.TeamMemberRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamCreateRequest {
    @Valid
    TeamInfoRequest teamInfoRequest;
    @Valid
    TeamMemberRequest teamMemberRequest;
    Long creatorId;
}
