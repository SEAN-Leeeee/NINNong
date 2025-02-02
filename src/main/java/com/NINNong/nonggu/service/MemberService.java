package com.NINNong.nonggu.service;

import com.NINNong.nonggu.domain.Member;
import com.NINNong.nonggu.entity.MemberEntity;
import com.NINNong.nonggu.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberService {
    private MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(PasswordEncoder passwordEncoder) {
       this.passwordEncoder = passwordEncoder;
    }

    public void signup(String email, String rawPassword, String name) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Member member = new Member(email, name, encodedPassword);
        MemberEntity memberEntity = MemberEntity.fromDomain(member);
        memberRepository.save(memberEntity);
    }

}
