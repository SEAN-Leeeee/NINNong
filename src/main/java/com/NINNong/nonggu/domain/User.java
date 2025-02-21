package com.NINNong.nonggu.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
@RequiredArgsConstructor
public class User {
    private String name;
    private String email;
    private String nickName;
    private String password;


    public void changePassword(String newPassword) {
        if (!StringUtils.hasText(newPassword)) {
            throw new IllegalArgumentException("비밀번호는 비어 있을 수 없습니다.");
        }
        this.password = newPassword;
    }

    public void changeName(String newName) {
        if (!StringUtils.hasText(newName)) {
            throw new IllegalArgumentException("이름은 비어 있을 수 없습니다.");
        }
        this.name = newName;
    }


}
