package com.sean.ninnong.team;

import com.sean.ninnong.teamMember.TeamMemberDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TeamRequest(
        @Valid TeamInfoDTO teamInfoDTO,
        @Valid TeamMemberDTO teamMemberDTO,
        Long creatorId) {
}
