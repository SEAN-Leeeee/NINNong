package com.sean.ninnong.teamMember;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
public class TeamMemberDTO {
    @NotNull @Min(1)
    private Long userId;
    private TeamMember.Role role;
    private TeamMember.Status status;
    private int backNumber;
}
