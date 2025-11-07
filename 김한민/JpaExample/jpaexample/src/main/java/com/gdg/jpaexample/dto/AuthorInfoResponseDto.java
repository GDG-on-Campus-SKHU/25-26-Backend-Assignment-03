package com.gdg.jpaexample.dto;

import com.gdg.jpaexample.domain.Author;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorInfoResponseDto {
    private Long id;
    private String name;
    private String nationality;

    public static AuthorInfoResponseDto from(Author author) {
        return AuthorInfoResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .nationality(author.getNationality())
                .build();
    }
}
