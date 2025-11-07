package com.example.jpawork.dto;

import com.example.jpawork.domain.Exercise_name;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Exercise_name_InfoResponseDto {
    private Long id;
    private String title;
    private Long exerciseAreaId;
    private String exerciseAreaName;

    public static Exercise_name_InfoResponseDto from(Exercise_name name) {
        return Exercise_name_InfoResponseDto.builder()
                .id(name.getId())
                .title(name.getTitle())
                .exerciseAreaId(name.getExerciseArea().getId())
                .exerciseAreaName(name.getExerciseArea().getName())
                .build();
    }
}
