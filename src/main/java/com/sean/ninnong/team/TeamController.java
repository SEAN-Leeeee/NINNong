package com.sean.ninnong.team;

import com.sean.ninnong.common.type.ApplicationStatus;
import com.sean.ninnong.team.dto.TeamCreateRequest;
import com.sean.ninnong.team.dto.TeamInfoRequest;
import com.sean.ninnong.team.dto.TeamResponse;
import com.sean.ninnong.team.dto.TeamMsgResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {


    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamMsgResponse> createTeam(@RequestBody @Valid TeamCreateRequest request) {
        Long teamId = teamService.of(request.getTeamInfoRequest(), request.getTeamMemberRequest(), request.getCreatorId());
        return ResponseEntity.status(HttpStatus.CREATED).body(TeamMsgResponse.createFrom(teamId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getTeam(@PathVariable Long id) {
        TeamResponse team = teamService.getTeam(id);

        return ResponseEntity.ok(team);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TeamResponse>> getTeamList() { /*여기에 팀 공개/비공개를 넣어야될까 ? */
        List<TeamResponse> teamList = teamService.getTeamList();
        return ResponseEntity.ok(teamList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeamMsgResponse> updateTeamInfo(@RequestBody TeamInfoRequest request,
                                                    @PathVariable Long id,
            /*시큐리티 붙이고 @AuthenticationPrincipal UserPrincipal user로 변경 */
                                                    @RequestParam Long userId) {
        teamService.updateOf(id, request, userId);
        return ResponseEntity.ok(TeamMsgResponse.updateFrom(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamMsgResponse> deleteTeam(@PathVariable Long id,
            /*시큐리티 붙이고 @AuthenticationPrincipal UserPrincipal user로 변경 */
                                                @RequestParam Long userId) {
        teamService.softDeleteTeam(id, userId);
        return ResponseEntity.ok(TeamMsgResponse.deleteFrom(id));
    }

    @PostMapping("/application/{id}")
    public ResponseEntity<TeamMsgResponse> applyTo(@PathVariable Long id, @RequestBody TeamApplication teamApplication) {

        teamService.applyWith(teamApplication);

        return ResponseEntity.ok(TeamMsgResponse.submittedApplication(id));
    }

    @DeleteMapping("/application/{id}")
    public ResponseEntity<TeamMsgResponse> cancelApplication(@PathVariable Long id, @RequestBody TeamApplication teamApplication) {
        //
        return ResponseEntity.ok(TeamMsgResponse.cancelApplication(id));
    }

    @PostMapping("/application/{applicationId}")
    public ResponseEntity<TeamMsgResponse> respondToApplication(@PathVariable Long applicationId, @RequestParam ApplicationStatus decision) {
        teamService.respondTo(applicationId, decision);

        return ResponseEntity.ok(TeamMsgResponse.applyOf(applicationId, decision));
    }


}
