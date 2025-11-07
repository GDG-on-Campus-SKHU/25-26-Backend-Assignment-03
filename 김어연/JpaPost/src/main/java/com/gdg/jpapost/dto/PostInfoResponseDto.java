package com.gdg.jpapost.dto;

import com.gdg.jpapost.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter

public class PostInfoResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String userName;

    public static PostInfoResponseDto from(Post post) {
        return PostInfoResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUser().getId())
                .userName(post.getUser().getName())
                .build();
    }
}
