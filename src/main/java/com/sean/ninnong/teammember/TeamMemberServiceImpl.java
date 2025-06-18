package com.sean.ninnong.teammember;

import com.sean.ninnong.team.TeamRepository;
import com.sean.ninnong.teammember.dto.TeamMemberRequest;
import org.springframework.stereotype.Service;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {
    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberServiceImpl(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    public void of(Long teamId, TeamMemberRequest request) {
        TeamMember member = TeamMember.of(teamId, request);
        teamMemberRepository.save(member);
    }
}
