package com.sean.ninnong.team;

import com.sean.ninnong.exception.UnauthorizedTeamAccessException;
import com.sean.ninnong.team.dto.TeamInfoRequest;
import com.sean.ninnong.team.dto.TeamListResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //fk를 어케 넣지
    private String name;
    private Long creator;
    private Long leader;
    private String region;
    private String logo;
    private String description;
    private TeamStatus isDeleted;

    public Team(TeamInfoRequest teamInfo, Long creatorId){
        this.creator = creatorId;
        this.leader = creatorId;
        this.name = teamInfo.getName();
        this.region = teamInfo.getRegion();
        this.logo = teamInfo.getLogo();
        this.description = teamInfo.getDescription();
        this.isDeleted = TeamStatus.ACTIVE;

    }
    public static Team createTeam(TeamInfoRequest teamInfo, Long creatorId){
       return new Team(teamInfo, creatorId);
    }

    public void softDeleteTeam() {
        this.isDeleted = TeamStatus.DELETED;
    }

    public enum TeamStatus { ACTIVE, DELETED}
    public void updateName(String name) { this.name = name;}
    public void updateRegion(String region) {
        this.region = region;
    }
    public void updateLogo(String logo) {
        this.logo = logo;
    }
    public void updateDescription(String description) {
        this.description = description;
    }
    public void updateLeader(Long id) {this.leader = id;}

    public Long getId() {
        return this.id;
    }


    public TeamListResponse toListResponse() {
        return new TeamListResponse(id, name, region, logo, description);
    }

    public void validateAuthorization(Long userId) {
        if (!this.leader.equals(userId)) {
            throw new UnauthorizedTeamAccessException(this.id);
        }
    }
}
