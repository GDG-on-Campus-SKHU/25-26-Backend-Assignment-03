package com.gdg.jpa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ToDoSaveRequestDto { //급한 일정을 아이디로 찾아 DB에 세부 일정 추가 요청
    @NotNull(message = "해당 일정은 존재하지 않습니다.") //Bean Validation 이용해 예외 처리
    private Long urgentWorkId;
    private String work;
}
