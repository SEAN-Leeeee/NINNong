package com.NINNong.nonggu.entity;

import com.NINNong.nonggu.domain.Member;
import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    protected MemberEntity() {}

    public MemberEntity(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static MemberEntity fromDomain(Member member) {
        return new MemberEntity(
            member.getEmail(),
            member.getName(),
            member.getPassword()
        );
    }
    // Getter만 제공
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
