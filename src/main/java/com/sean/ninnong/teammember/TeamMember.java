package com.sean.ninnong.teammember;

import com.sean.ninnong.common.type;
import com.sean.ninnong.teammember.dto.TeamMemberRequest;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class TeamMember {
    public enum MemberStatus { PENDING, ACTIVE, STOPPED, LEAVE }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long teamId;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private type.Role role;
    private int backNumber;
    private LocalDateTime joinedAt;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public TeamMember(Long teamId, TeamMemberRequest info) {
        this.userId = info.getUserId();
        this.teamId = teamId;
        this.role = info.getRole();
        this.joinedAt = LocalDateTime.now();
        this.status = info.getStatus();
    }

    public static TeamMember of(Long teamId, TeamMemberRequest info) {
        return new TeamMember(teamId, info);
    }

    public void asLeader() {
        this.role = type.Role.LEADER;
    }

 /*   public TeamMember kickMember() {

    }

    public TeamMember changeStatus() {

    }
*/
    public void updateBackNumber(int backNumber) {
        this.backNumber = backNumber;
    }

    public void updateRole(type.Role role) {
        this.role = role;
    }

    public void updateStatus(MemberStatus status) {
        this.status = status;
    }
}
