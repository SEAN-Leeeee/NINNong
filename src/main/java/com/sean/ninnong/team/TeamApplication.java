package com.sean.ninnong.team;

import com.sean.ninnong.common.type.ApplicationStatus;
import com.sean.ninnong.common.type.DraftLevel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TeamApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long teamId;
    private Long applicantId;
    private String name;
    @Enumerated(EnumType.STRING)
    private DraftLevel draftLevel;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private LocalDateTime createdAt;


    void decideOfApply(ApplicationStatus decision) {
        this.status = decision;
    }
}
