package com.example.jpaexample.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SnackSaveRequestDto {
    private String sName;
    private Long companyId;
}
