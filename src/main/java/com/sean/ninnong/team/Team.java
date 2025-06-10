package com.sean.ninnong.team;

import com.sean.ninnong.teamMember.TeamMember;
import com.sean.ninnong.teamMember.TeamMemberDTO;
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
    private Long creator;
    private String name;
    private String region;
    private String logo;
    private String text;

    public Team(TeamInfoDTO teamInfo, Long creatorId){
        this.creator = creatorId;
        this.name = teamInfo.getName();
        this.region = teamInfo.getRegion();
        this.logo = teamInfo.getLogo();
        this.text = teamInfo.getText();
    }
    public static Team createTeam(TeamInfoDTO teamInfo, Long creatorId){
       return new Team(teamInfo, creatorId);
    }

    public Team updateInfo(Team team, TeamInfo teamInfo) {
        team.name = teamInfo.name();
        team.region = teamInfo.region();
        team.logo = teamInfo.logo();
        team.text = teamInfo.text();

        return team;
    }

    public Long getId() {
        return this.id;
    }
}
