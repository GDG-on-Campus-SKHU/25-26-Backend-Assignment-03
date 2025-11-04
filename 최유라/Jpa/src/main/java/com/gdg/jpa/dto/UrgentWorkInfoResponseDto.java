package com.gdg.jpa.dto;

import com.gdg.jpa.domain.UrgentWork;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UrgentWorkInfoResponseDto {
    private Long id;
    private String schedule;
    private int daysLeft;

    public static UrgentWorkInfoResponseDto from(UrgentWork urgentWork) {
        return UrgentWorkInfoResponseDto.builder()
                .id(urgentWork.getId())
                .schedule(urgentWork.getSchedule())
                .daysLeft(urgentWork.getDaysLeft())
                .build();
    }
}
