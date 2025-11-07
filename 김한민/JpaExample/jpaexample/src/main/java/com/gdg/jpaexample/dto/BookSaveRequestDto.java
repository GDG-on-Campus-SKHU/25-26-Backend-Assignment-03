package com.gdg.jpaexample.dto;

import lombok.Getter;

@Getter
public class BookSaveRequestDto { // 생성 전용
    private String title;
    private int publishedYear;
    private Long authorId;  // 생성시에만 필요
}
