package com.example.seungchang.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    // Restaurant 200 Ok
    RESTAURANT_FOUND(HttpStatus.OK, "식당 조회 성공"),
    RESTAURANT_UPDATED(HttpStatus.OK, "식당 수정 성공"),
    RESTAURANT_LIST_FOUND(HttpStatus.OK, "전체 식당 조회 성공"),

    // Food 200 ok
    FOOD_FOUND(HttpStatus.OK, "식당 조회 성공"),
    FOOD_UPDATED(HttpStatus.OK, "식당 수정 성공"),
    FOOD_SOLD(HttpStatus.OK, "음식 판매"),

    // 201 Created, Delete
    RESTAURANT_CREATED(HttpStatus.CREATED, "식당 생성 성공"),
    RESTAURANT_DELETED(HttpStatus.NO_CONTENT, "식당 삭제 성공"),
    FOOD_CREATED(HttpStatus.CREATED, "식당 생성 성공"),
    FOOD_DELETED(HttpStatus.NO_CONTENT, "식당 삭제 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
