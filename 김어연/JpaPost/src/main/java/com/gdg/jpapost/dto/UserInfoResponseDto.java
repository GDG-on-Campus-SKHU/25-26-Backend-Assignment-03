package com.gdg.jpapost.dto;

import com.gdg.jpapost.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter

public class UserInfoResponseDto {
    private Long id;
    private String name;
    private String email;

    public static UserInfoResponseDto from(User user) {
        return UserInfoResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
