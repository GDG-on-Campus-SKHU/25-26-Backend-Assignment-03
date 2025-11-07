package com.gdg.jpaexample.dto;

import com.gdg.jpaexample.domain.Major;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MajorInfoResponseDto {
    private Long id;
    private String name;

    public static MajorInfoResponseDto from(Major major) {
        return MajorInfoResponseDto.builder()
                .id(major.getId())
                .name(major.getName())
                .build();
    }
}
