package com.sean.ninnong.team;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {


    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody @Valid TeamRequest request) {
        Long teamId = teamService.createTeam(request.teamInfoDTO(), request.teamMemberDTO(), request.creatorId());
        return ResponseEntity.status(HttpStatus.CREATED).body(TeamResponse.createFrom(teamId));
    }


}
