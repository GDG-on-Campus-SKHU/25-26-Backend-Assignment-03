package com.example.jpaexample.dto;

import lombok.Getter;

@Getter
public class SnackSaveRequestDto {
    private String name;
    private int releaseYear;
    private Long companyId;
}
