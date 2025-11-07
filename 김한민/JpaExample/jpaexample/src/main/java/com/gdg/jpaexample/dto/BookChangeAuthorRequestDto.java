package com.gdg.jpaexample.dto;
import lombok.Getter;

@Getter
public class BookChangeAuthorRequestDto { //저자만 교체 전용
    private Long authorId;
}
