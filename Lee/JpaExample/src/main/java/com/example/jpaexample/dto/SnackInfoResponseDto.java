package com.example.jpaexample.dto;

import com.example.jpaexample.domain.Snack;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SnackInfoResponseDto {
    private Long id;
    private String name;
    private int releaseYear;
    private Long companyId;
    private String companyName;

    public static SnackInfoResponseDto from(Snack snack) {
        return SnackInfoResponseDto.builder()
                .id(snack.getId())
                .name(snack.getName())
                .releaseYear(snack.getReleaseYear())
                .companyId(snack.getCompany().getId())
                .companyName(snack.getCompany().getName())
                .build();
    }
}
