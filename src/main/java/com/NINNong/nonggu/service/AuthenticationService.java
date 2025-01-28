package com.NINNong.nonggu.service;

import com.NINNong.nonggu.domain.Member;
import com.NINNong.nonggu.entity.MemberEntity;
import com.NINNong.nonggu.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(String email, String rawPassword, String name) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Member member = new Member(email, name, encodedPassword);
        MemberEntity memberEntity = MemberEntity.fromDomain(member);
        memberRepository.save(memberEntity);
    }

    public  boolean login(String email, String rawPassword) {
        MemberEntity member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        return passwordEncoder.matches(rawPassword, member.getPassword());
    }

//    public boolean validatePassword(String rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
}
