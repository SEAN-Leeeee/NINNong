package com.NINNong.nonggu.dto;

import com.NINNong.nonggu.entity.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserSignupRequest {

    private String email;
    private String name;
    private String password;
    private String nickName;

    public static UserEntity toEntity(UserSignupRequest request) {
        return new UserEntity().builder()
                .email(request.email)
                .name(request.name)
                .password(request.password)
                .nickName(request.nickName)
                .isConfirmed(false)
                .build();
    }

    public void replaceToEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public String getPassword() {
        return password;
    }
}
