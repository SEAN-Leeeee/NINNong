package com.sean.ninnong.team;

import com.sean.ninnong.team.dto.TeamInfoRequest;
import com.sean.ninnong.team.dto.TeamListResponse;
import com.sean.ninnong.teammember.dto.TeamMemberRequest;

import java.util.List;

public interface TeamService {
    Long of(TeamInfoRequest teamInfo, TeamMemberRequest memberInfo, Long creatorId);

    List<TeamListResponse> getTeamList();

    void updateTeamInfo(TeamInfoRequest teamInfo, Long teamId, Long userId);

    void softDeleteTeam(Long teamId, Long userId);
}
