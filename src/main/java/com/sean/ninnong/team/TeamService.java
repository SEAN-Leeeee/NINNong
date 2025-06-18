package com.sean.ninnong.team;

import com.sean.ninnong.common.type.ApplicationStatus;
import com.sean.ninnong.team.dto.TeamInfoRequest;
import com.sean.ninnong.team.dto.TeamResponse;
import com.sean.ninnong.teammember.dto.TeamMemberRequest;

import java.util.List;

public interface TeamService {
    Long of(TeamInfoRequest teamInfo, TeamMemberRequest memberInfo, Long creatorId);

    TeamResponse getTeam(Long teamId);

    List<TeamResponse> getTeamList();

    void updateOf(Long teamId, TeamInfoRequest teamInfo , Long userId);

    void softDeleteTeam(Long teamId, Long userId);

    void applyWith(TeamApplication teamApplication);

    List<TeamApplication> getApplicationList(Long id);

    void respondTo(Long applicationId, ApplicationStatus responseStatus);

}
