package com.NINNong.nonggu.service;

import com.NINNong.nonggu.domain.User;
import com.NINNong.nonggu.entity.UserEntity;
import com.NINNong.nonggu.repository.UserRepository;
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
class UserServiceTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private String email = "siyeon@naver.com";
    private String name = "siyeon";
    private String rawPassword = "1234";
    private UserEntity savedMember;

    @BeforeEach
    public void setUp() {
        String encodedPassword = passwordEncoder.encode(rawPassword);

        User user = new User(email, name, encodedPassword);
        UserEntity userEntity = UserEntity.fromDomain(user);
        savedMember = userRepository.save(userEntity);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    private UserEntity getMemberEntity(String email) {
        return userRepository.findByEmail(email)
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
