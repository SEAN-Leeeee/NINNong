package com.NINNong.nonggu.entity;

import com.NINNong.nonggu.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Entity
@Access(AccessType.FIELD)
@Builder
@RequiredArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private Boolean isConfirmed;

    public UserEntity(Object email, String name, String password, String nickName, Boolean isConfirmed) {
    }
}
