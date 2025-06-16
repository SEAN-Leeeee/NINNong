package com.sean.ninnong.team;

import com.sean.ninnong.team.dto.TeamCreateRequest;
import com.sean.ninnong.team.dto.TeamInfoRequest;
import com.sean.ninnong.team.dto.TeamListResponse;
import com.sean.ninnong.team.dto.TeamResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {


    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody @Valid TeamCreateRequest request) {
        Long teamId = teamService.of(request.getTeamInfoRequest(), request.getTeamMemberRequest(), request.getCreatorId());
        return ResponseEntity.status(HttpStatus.CREATED).body(TeamResponse.createFrom(teamId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<TeamListResponse>> teamList() {
        List<TeamListResponse> teamList = teamService.getTeamList();
        return ResponseEntity.ok(teamList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeamResponse> updateTeamInfo(@RequestBody TeamInfoRequest request,
                                                       @PathVariable Long id, @AuthenticationPrincipal UserPrincipal user) {
        teamService.updateTeamInfo(request, id, user.getUserId());
        return ResponseEntity.ok(TeamResponse.updateFrom(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamResponse> deleteTeam(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal user) {
        teamService.softDeleteTeam(id, user.getUserId);
        return ResponseEntity.ok(TeamResponse.deleteFrom(id));
    }
}
