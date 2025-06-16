package com.sean.ninnong.teammember;

import com.sean.ninnong.teammember.dto.TeamMemberRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class TeamMember {
    public enum Role {LEADER, MEMBER, GUEST}
    public enum Status { PENDING, ACTIVE, STOPPED, LEAVE }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long teamId;
    private Long userId;
    private Role role;
    private int backNumber;
    private LocalDateTime joinedAt;
    private Status status;

    public TeamMember(TeamMemberRequest info, Long teamId) {
        this.userId = info.getUserId();
        this.teamId = teamId;
        this.role = info.getRole();
        this.backNumber = info.getBackNumber();
        this.joinedAt = LocalDateTime.now();
        this.status = info.getStatus();
    }

    public static TeamMember of(TeamMemberRequest info, Long teamId) {
        return new TeamMember(info, teamId);
    }

    public void asLeader() {
        this.role = Role.LEADER;
    }
    public void updateTeamId(Long teamId) {this.teamId = teamId;}

 /*   public TeamMember kickMember() {

    }

    public TeamMember changeStatus() {

    }
*/
    public void updateBackNumber(int backNumber) {
        this.backNumber = backNumber;
    }

    public void updateRole(Role role) {
        this.role = role;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }
}
