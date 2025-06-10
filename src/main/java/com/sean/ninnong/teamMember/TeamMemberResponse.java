package com.sean.ninnong.teamMember;

import java.time.LocalDateTime;

public record TeamMemberResponse(Long memberId, String message, LocalDateTime processedAt) {

    public static TeamMemberResponse addFrom(Long memberId) {
        return new TeamMemberResponse(
                memberId,
                "멤버가 추가되었습니다.",
                LocalDateTime.now()
        );
    }

    public static TeamMemberResponse updateFrom(Long memberId) {
        return new TeamMemberResponse(
                memberId,
                "멤버의 정보가 변경되었습니다.",
                LocalDateTime.now()
        );
    }

    public static TeamMemberResponse  deleteFrom(Long memberId) {
        return new TeamMemberResponse(
                memberId,
                "멤버가 팀에서 삭제되었습니다.",
                LocalDateTime.now()
        );
    }
}
