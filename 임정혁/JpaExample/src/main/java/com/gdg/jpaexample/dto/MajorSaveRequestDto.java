package com.gdg.jpaexample.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MajorSaveRequestDto {
    @NotBlank(message = "전공명은 필수입니다.")
    private String name;
}
