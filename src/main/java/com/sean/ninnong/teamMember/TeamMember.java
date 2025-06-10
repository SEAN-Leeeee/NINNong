package com.sean.ninnong.teamMember;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class TeamMember {
    enum Role {LEADER, MEMBER, GUEST}
    enum Status { PENDING, ACTIVE, STOPPED, LEAVE }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long teamId;
    private Long userId;
    private Role role;
    private int backNumber;
    private LocalDateTime joinedAt;
    private Status status;

    public TeamMember(TeamMemberDTO info, Long teamId) {
        this.userId = info.getUserId();
        this.teamId = teamId;
        this.role = info.getRole();
        this.backNumber = info.getBackNumber();
        this.joinedAt = LocalDateTime.now();
        this.status = info.getStatus();
    }

    public static TeamMember of(TeamMemberDTO info, Long teamId) {
        return new TeamMember(info, teamId);
    }

    public void changeLeader() {
        this.role = Role.LEADER;
    }
    public void updateTeamId(Long teamId) {this.teamId = teamId;}

//    public TeamMember kickMember() {
//
//    }
//
//    public TeamMember changeStatus() {
//
//    }

    public TeamMember updateInfo() { //단순 정보 변경 등번호라던가

    }
}
