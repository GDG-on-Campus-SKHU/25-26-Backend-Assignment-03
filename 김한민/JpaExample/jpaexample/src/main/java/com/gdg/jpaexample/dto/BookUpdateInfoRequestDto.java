package com.gdg.jpaexample.dto;

import lombok.Getter;

@Getter
public class BookUpdateInfoRequestDto { // 기본 정보 수정 전용
        private String title;
        private int publishedYear;
}
