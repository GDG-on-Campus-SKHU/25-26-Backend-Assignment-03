package com.gdg.jpa.dto;

import lombok.Getter;

@Getter
public class ToDoSaveRequestDto { //급한 일정을 아이디로 찾아 DB에 세부 일정 추가 요청
    private Long soonId;
    private String work;
}
