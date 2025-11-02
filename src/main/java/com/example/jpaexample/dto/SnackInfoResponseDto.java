package com.example.jpaexample.dto;

import com.example.jpaexample.domain.Snack;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SnackInfoResponseDto {
    private Long id;
    private String sName;
    private Long companyId;
    private String companyName;

    public static SnackInfoResponseDto from(Snack snack) {
        return SnackInfoResponseDto.builder()
                .id(snack.getId())
                .sName(snack.getS_name())
                .companyId(snack.getCompany().getId())
                .companyName(snack.getCompany().getName())
                .build();
    }
}
