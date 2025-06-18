package com.sean.ninnong.teammember.dto;

import com.sean.ninnong.common.type;
import com.sean.ninnong.teammember.TeamMember;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TeamMemberRequest {
    @NotNull @Min(1)
    private Long userId;
    private type.Role role = type.Role.MEMBER;
    private TeamMember.MemberStatus status = TeamMember.MemberStatus.PENDING;
}
