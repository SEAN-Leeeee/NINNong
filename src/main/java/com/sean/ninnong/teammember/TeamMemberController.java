package com.sean.ninnong.teammember;

import com.sean.ninnong.teammember.dto.TeamMemberRequest;
import com.sean.ninnong.teammember.dto.TeamMemberResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teamMember")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    public TeamMemberController(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    @PostMapping("/{teamId}")
    public ResponseEntity<TeamMemberResponse> addMTeamMember(@PathVariable Long teamId, @RequestBody TeamMemberRequest request) {
        teamMemberService.of(teamId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(TeamMemberResponse.addFrom(teamId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeamMemberResponse> updateTeamMember(@PathVariable Long id, @RequestBody @Valid TeamMemberRequest teamMemberRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(TeamMemberResponse.updateFrom(id));
    }


}
