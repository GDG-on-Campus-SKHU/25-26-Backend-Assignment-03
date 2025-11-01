package com.gdg.jpaexample.dto;

import com.gdg.jpaexample.domain.Singer;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SingerInfoResponseDto {
    private Long id;
    private String name;
    private int debutYear;

    public static SingerInfoResponseDto from(Singer singer) {
        return SingerInfoResponseDto.builder()
                .id(singer.getId())
                .name(singer.getName())
                .debutYear(singer.getDebutYear())
                .build();
    }
}
