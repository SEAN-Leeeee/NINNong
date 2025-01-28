package com.NINNong.nonggu.service;

import com.NINNong.nonggu.entity.MemberEntity;
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
        String name = "siyeon lee";
        String rawPassword = "1234";

        PasswordEncoder encoder = new PasswordEncoder();

        //when
        String encodedPassword = encoder.encode(rawPassword);
        Member member = new Member(email, name, encodedPassword);
        MemberEntity memberEntity = memberEntity.fromDomain(member);

        memberRepository.save(memberEntity);
        //then
        // 뭘 검증할 수 있을까??
    }
}
