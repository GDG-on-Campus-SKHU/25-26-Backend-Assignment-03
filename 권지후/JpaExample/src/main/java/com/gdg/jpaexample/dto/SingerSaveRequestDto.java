package com.gdg.jpaexample.dto;

import com.gdg.jpaexample.domain.Singer;
import lombok.Getter;

@Getter
public class SingerSaveRequestDto {
    private String name;
    private int debutYear;
}
