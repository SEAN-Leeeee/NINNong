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
class MemberServiceTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    private String email = "siyeon@naver.com";
    private String name = "siyeon";
    private String rawPassword = "1234";
    private MemberEntity savedMember;

    @BeforeEach
    public void setUp() {
        String encodedPassword = passwordEncoder.encode(rawPassword);

        Member member = new Member(email, name, encodedPassword);
        MemberEntity memberEntity = MemberEntity.fromDomain(member);
        savedMember = memberRepository.save(memberEntity);
    }

    @AfterEach
    public void tearDown() {
        memberRepository.deleteAll();
    }

    private MemberEntity getMemberEntity(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Member not found"));
    }


    @Test
    @DisplayName("MemberEntity Id Should not be null after saving")
    public void savedMemberShouldNotBeNull() {

        //then
        assertNotNull(savedMember);
    }

    @Test
    @DisplayName("email should match")
    public void emailShouldMatch() {

        //then
        assertEquals(email, getMemberEntity(email).getEmail());
    }

    @Test
    @DisplayName("name should match")
    public void nameShouldMatch() {
        //then
        assertEquals(name,  getMemberEntity(email).getName());
    }

    @Test
    @DisplayName("Stored email should match")
    public void storedEmailShouldMatch() {
        //then
        assertEquals(savedMember.getEmail(), getMemberEntity(email).getEmail());
    }

    @Test
    @DisplayName("Stored name should match")
    public void storedNameShouldMatch() {
        //then
        assertEquals(savedMember.getName(), getMemberEntity(email).getName());
    }
}
