package com.sean.ninnong.team;

import java.time.LocalDateTime;

public record TeamResponse(Long teamId, String message, LocalDateTime processedAt) {
    public static TeamResponse createFrom(Long teamId) {
        return new TeamResponse(
                teamId,
                "팀이 생성되었습니다.",
                LocalDateTime.now()
        );
    }

    public static TeamResponse updateFrom(Long teamId) {
        return new TeamResponse(
                teamId,
                "팀의 정보가 수정되었습니다.",
                LocalDateTime.now()
        );
    }

    public static TeamResponse deleteFrom(Long teamId) {
        return new TeamResponse(
                teamId,
                "팀이 삭제 되었습니다.",
                LocalDateTime.now()
        );
    }
}
