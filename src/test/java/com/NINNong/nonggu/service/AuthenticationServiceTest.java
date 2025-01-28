package com.NINNong.nonggu.service;

import com.NINNong.nonggu.domain.Member;
import com.NINNong.nonggu.entity.MemberEntity;
import com.NINNong.nonggu.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class AuthenticationServiceTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        System.out.println("test start!");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("test end!");
    }

    @Test
    @DisplayName("Sign up Succeeds when all fields are filled")
    public void ifAllFieldsAreFilledThenSignUpShouldSucceed() {
        //given
        String email = "siyeon@naver.com";
        String name = "siyeon";
        String rawPassword = "1234";

        String encodedPassword = passwordEncoder.encode(rawPassword);

        Member member = new Member(email, name, encodedPassword);
        MemberEntity memberEntity = MemberEntity.fromDomain(member);

        //When
        MemberEntity savedMemberEntity = memberRepository.save(memberEntity);

        //then
        assertNotEquals(rawPassword, savedMemberEntity.getPassword(), "Password should be encrypted");

        assertNotNull(savedMemberEntity.getId(), "MemberEntity Id Should not be null after saving");
        assertEquals(email, savedMemberEntity.getEmail(), "email should match");
        assertEquals(name, savedMemberEntity.getName(), "name should match");

        MemberEntity foundMemberEntity = memberRepository.findById(savedMemberEntity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found in repository"));

        assertEquals(savedMemberEntity.getEmail(), foundMemberEntity.getEmail(), "Stored email should match");
        assertEquals(savedMemberEntity.getName(), foundMemberEntity.getName(), "Stored name should match");

      /*  PasswordEncoder encoder = new PasswordEncoder();

        //when
        String encodedPassword = encoder.encode(rawPassword);
        Member member = new Member(email, name, encodedPassword);
        MemberEntity memberEntity = memberEntity.fromDomain(member);

        memberRepository.save(memberEntity);*/
        //then
        // 뭘 검증할 수 있을까??
    }
}
