package com.gdg.jpaexample.dto.daydto;

import com.gdg.jpaexample.domain.Day;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class DayInfoResponseDto {
    private Long id;
    private int day;
    private int month;

    public static DayInfoResponseDto from(Day day){
        return DayInfoResponseDto.builder()
                .id(day.getId())
                .day(day.getDay())
                .month(day.getMonth())
                .build();
    }
}
