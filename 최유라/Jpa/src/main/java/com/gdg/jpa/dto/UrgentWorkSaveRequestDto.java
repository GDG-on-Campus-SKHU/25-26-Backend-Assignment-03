package com.gdg.jpa.dto;

import lombok.Getter;

@Getter
public class UrgentWorkSaveRequestDto { //급한 일정(first)과 남은 일수(limit)추가 요청
    private String schedule;
    private int daysLeft;
}
