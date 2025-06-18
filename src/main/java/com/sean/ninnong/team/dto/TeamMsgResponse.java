package com.sean.ninnong.team.dto;

import com.sean.ninnong.common.type.ApplicationStatus;

import java.time.LocalDateTime;

public record TeamMsgResponse(Long id, String message, LocalDateTime processedAt) {
    public static TeamMsgResponse createFrom(Long id) {
        return new TeamMsgResponse(
                id,
                "팀이 생성되었습니다.",
                LocalDateTime.now()
        );
    }

    public static TeamMsgResponse updateFrom(Long id) {
        return new TeamMsgResponse(
                id,
                "팀의 정보가 수정되었습니다.",
                LocalDateTime.now()
        );
    }

    public static TeamMsgResponse deleteFrom(Long id) {
        return new TeamMsgResponse(
                id,
                "팀이 삭제 되었습니다.",
                LocalDateTime.now()
        );
    }

    public static TeamMsgResponse submittedApplication(Long id) {
        return new TeamMsgResponse(
                id,
                "팀에 지원서를 제출했습니다.",
                LocalDateTime.now()
        );
    }

    public static TeamMsgResponse cancelApplication(Long id) {
        return new TeamMsgResponse(
                id,
                "팀 가입 요청을 취소했습니다.",
                LocalDateTime.now()
        );
    }
    public static TeamMsgResponse applyOf(Long applicationId, ApplicationStatus status) {
        return new TeamMsgResponse(
                applicationId,
                "팀 가입 요청이 " + status + "되었습니다.",
                LocalDateTime.now()
        );
    }


}
