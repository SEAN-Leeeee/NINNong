package com.sean.ninnong.teammember;

import com.sean.ninnong.teammember.dto.TeamMemberRequest;

public interface TeamMemberService {
    void of(Long teamId, TeamMemberRequest request);
}
