package com.sean.ninnong.team;

import com.sean.ninnong.teamMember.TeamMember;
import com.sean.ninnong.teamMember.TeamMemberDTO;
import com.sean.ninnong.teamMember.TeamMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {

        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    public Long createTeam(TeamInfoDTO teamInfo, TeamMemberDTO memberInfo, Long creatorId) {
        Team team = Team.createTeam(teamInfo, creatorId);
        teamRepository.save(team);
        Long teamId = team.getId();

        addMember(memberInfo, teamId);

        return teamId;
    }

    private void addMember(TeamMemberDTO memberInfo, Long teamId) {
        TeamMember member = TeamMember.of(memberInfo, teamId);
        member.changeLeader();

        teamMemberRepository.save(member);
    }
}
