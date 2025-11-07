package com.gdg.jpa.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ToDoSaveRequestDto { //급한 일정을 아이디로 찾아 DB에 세부 일정 추가 요청
    @NotNull(message = "workId에는 NULL값이 입력될 수 없습니다.") //Bean Validation은 입력받은 데이터의 조건 만족 여부만 판단
    private Long urgentWorkId;
    private String work;
}
