package com.sean.ninnong.team;

import com.sean.ninnong.common.type.ApplicationStatus;
import com.sean.ninnong.exception.ApplicationNotFoundException;
import com.sean.ninnong.exception.TeamNotFoundException;
import com.sean.ninnong.team.dto.TeamInfoRequest;
import com.sean.ninnong.team.dto.TeamResponse;
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
    private final TeamApplicationRepository teamApplicationRepository;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository, TeamApplicationRepository teamApplicationRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.teamApplicationRepository = teamApplicationRepository;
    }

    private Team findTeamByIdOrElseThrow(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    @Override
    public Long of(TeamInfoRequest teamInfo, TeamMemberRequest memberInfo, Long creatorId) {
        Team team = Team.createTeam(teamInfo, creatorId);
        teamRepository.save(team);
        Long id = team.getId();

        makeLeaderOf(id, memberInfo);

        return id;
    }

    private void makeLeaderOf(Long id, TeamMemberRequest info) {
        TeamMember member = TeamMember.of(id, info);
        member.asLeader();

        teamMemberRepository.save(member);
    }

    @Override
    public TeamResponse getTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        return team.toResponse();
    }

    @Override
    public List<TeamResponse> getTeamList() {
        List<Team> teamList = teamRepository.findAll();
        return teamList
                .stream()
                .map(Team::toResponse)
                .toList();
    }

    @Override
    public void updateOf(Long id, TeamInfoRequest teamInfoRequest, Long userId) {

        Team team = findTeamByIdOrElseThrow(id);
        team.validateAuthorization(userId);

        team.updateName(teamInfoRequest.getName());
        team.updateRegion(teamInfoRequest.getRegion());
        team.updateLogo(teamInfoRequest.getName());
        team.updateDescription(teamInfoRequest.getDescription());
    }

    @Override
    public void softDeleteTeam(Long id, Long userId) {
        Team team = findTeamByIdOrElseThrow(id);
        team.validateAuthorization(userId);

        team.softDeleteTeam();
        teamRepository.save(team);
    }

    @Override
    public void applyWith(TeamApplication application) {
        teamApplicationRepository.save(application);
    }

    @Override
    public List<TeamApplication> getApplicationList(Long id) {
        return teamApplicationRepository.findAll();
    }

    @Override
    public void respondTo(Long applicationId, ApplicationStatus decision) {
        TeamApplication application = teamApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(applicationId));

        application.decideOfApply(decision);
        teamApplicationRepository.save(application);
    }


}
