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

    @PatchMapping("/{id}")
    public ResponseEntity<TeamMemberResponse> updateTeamMember(@PathVariable Long id, @RequestBody @Valid TeamMemberRequest teamMemberRequest) {


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(TeamMemberResponse.updateFrom(id));
    }
}
