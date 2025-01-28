package com.NINNong.nonggu.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "즐농 프로젝트",
                version = "1.0",
                description = "여자 농구 동호회 커뮤니티 NINNong의  API 문서입니다."
        )
)
public class SwaggerConfig {
    // 추가 설정이 필요한 경우 여기에 작성
}
