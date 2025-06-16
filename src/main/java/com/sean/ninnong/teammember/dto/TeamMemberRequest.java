package com.sean.ninnong.teammember.dto;

import com.sean.ninnong.teammember.TeamMember;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TeamMemberRequest {
    @NotNull @Min(1)
    private Long userId;
    private TeamMember.Role role;
    private TeamMember.Status status;
    private int backNumber;
}
