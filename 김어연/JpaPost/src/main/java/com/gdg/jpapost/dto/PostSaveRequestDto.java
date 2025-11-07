package com.gdg.jpapost.dto;

import lombok.Getter;

@Getter

public class PostSaveRequestDto {
    private Long userId;
    private String title;
    private String content;
}
