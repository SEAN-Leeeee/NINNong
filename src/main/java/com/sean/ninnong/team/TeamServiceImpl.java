package com.sean.ninnong.team;

import com.sean.ninnong.exception.TeamNotFoundException;
import com.sean.ninnong.team.dto.TeamInfoRequest;
import com.sean.ninnong.team.dto.TeamListResponse;
import com.sean.ninnong.teammember.TeamMember;
import com.sean.ninnong.teammember.dto.TeamMemberRequest;
import com.sean.ninnong.teammember.TeamMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    private Team findTeamByIdOrElseThrow(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    public Long of(TeamInfoRequest teamInfo, TeamMemberRequest memberInfo, Long creatorId) {
        Team team = Team.createTeam(teamInfo, creatorId);
        teamRepository.save(team);
        Long teamId = team.getId();

        makeLeader(memberInfo, teamId);

        return teamId;
    }

    private void makeLeader(TeamMemberRequest memberInfo, Long teamId) {
        TeamMember member = TeamMember.of(memberInfo, teamId);
        member.asLeader();

        teamMemberRepository.save(member);
    }

    public List<TeamListResponse> getTeamList() {
        List<Team> teamList = teamRepository.findAll();
        return teamList
                .stream()
                .map(Team::toListResponse)
                .toList();

    }

    public void updateTeamInfo(TeamInfoRequest teamInfoRequest, Long teamId, Long userId) {

        Team team = findTeamByIdOrElseThrow(teamId);
        team.validateAuthorization(userId);

        team.updateName(teamInfoRequest.getName());
        team.updateRegion(teamInfoRequest.getRegion());
        team.updateLogo(teamInfoRequest.getName());
        team.updateDescription(teamInfoRequest.getDescription());
    }

    public void softDeleteTeam(Long teamId, Long userId) {
        Team team = findTeamByIdOrElseThrow(teamId);
        team.validateAuthorization(userId);

        team.softDeleteTeam();
        teamRepository.save(team);
    }

}
