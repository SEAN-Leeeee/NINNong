package com.sean.ninnong.team;

import com.sean.ninnong.teamMember.TeamMemberDTO;

public interface TeamService {
    Long createTeam(TeamInfoDTO teamInfo, TeamMemberDTO memberInfo, Long creatorId);

}
