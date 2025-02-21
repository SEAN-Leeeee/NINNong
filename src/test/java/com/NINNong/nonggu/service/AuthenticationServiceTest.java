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
class AuthenticationServiceTest {

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
    @DisplayName("Password should be encrypted")
    public void passwordShouldBeEncrypted() {
        // when
        UserEntity member = getMemberEntity(email);
        //then
        assertNotEquals(rawPassword, member.getPassword());
        assertTrue(passwordEncoder.matches(rawPassword, member.getPassword()));

    }




}
