package com.example.jpawork.dto;

import com.example.jpawork.domain.Exercise_area;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Exercise_area_InfoResponseDto {
    private Long id;
    private String name;
    private int countWeek;

    public static Exercise_area_InfoResponseDto from(Exercise_area area) {
        return Exercise_area_InfoResponseDto.builder()
                .id(area.getId())
                .name(area.getName())
                .countWeek(area.getCountWeek())
                .build();
    }
}
