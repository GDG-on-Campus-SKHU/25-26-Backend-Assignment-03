package com.gdg.jpa.dto;

import com.gdg.jpa.domain.ToDo;
import lombok.Builder;
import lombok.Getter;

@Builder //이게 아마 데이터 생성해주는 것인 듯?
@Getter
public class ToDoInfoResponseDto { //MusicSaveRequestDTO 요청에 대한 대응
    private Long id;
    private String work;
    private Long soonId;
    private String soonFirst;

    public static ToDoInfoResponseDto from(ToDo todo){ //from(도메인 객체)->도메인 객체를 from 앞에 있는 객체?로 변환
        return ToDoInfoResponseDto.builder()
                .id(todo.getId())
                .work(todo.getWork())
                .soonId(todo.getSoon().getId())
                .soonFirst(todo.getSoon().getFirst())
                .build();
    }
}
