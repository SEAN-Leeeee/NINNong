package com.sean.ninnong.team;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TeamInfoDTO {

    @NotBlank(message = "팀 이름을 비울 수 없습니다.")
    private String name;
    private String logo; // type을 확인하고싶다
    private String region;
    private String text;//feature? explain?

}
