package com.gdg.jpaexample.dto;

import com.gdg.jpaexample.domain.Music;
import com.gdg.jpaexample.domain.Singer;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MusicInfoResponseDto {
    private Long id;
    private String title;
    private Long singerId;
    private String singerName;

    public static MusicInfoResponseDto from(Music music) {
        return MusicInfoResponseDto.builder()
                .id(music.getId())
                .title(music.getTitle())
                .singerId(music.getSinger().getId())
                .singerName(music.getSinger().getName())
                .build();
    }
}
