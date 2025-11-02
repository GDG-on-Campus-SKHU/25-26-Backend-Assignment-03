package com.example.jpaexample.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanySaveRequestDto {
    private String name;
    private int foundingYear;
}
