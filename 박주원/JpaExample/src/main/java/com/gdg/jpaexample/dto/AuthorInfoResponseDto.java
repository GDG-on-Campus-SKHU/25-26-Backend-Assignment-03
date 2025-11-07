package com.gdg.jpaexample.dto;

import com.gdg.jpaexample.domain.Author;
import com.gdg.jpaexample.domain.Author;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthorInfoResponseDto {
    private Long id;
    private String name;
    private int birthYear;

    public static AuthorInfoResponseDto from(Author author) {
        return AuthorInfoResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .birthYear(author.getBirthYear())
                .build();
    }
}
