package com.gdg.jpaexample.dto;

import lombok.Getter;

@Getter
public class BookSaveRequestDto {
    private String title;
    private int publishedYear;
    private Long authorId;
}
