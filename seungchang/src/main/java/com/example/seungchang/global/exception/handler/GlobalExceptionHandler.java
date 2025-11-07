package com.example.seungchang.global.exception.handler;

import com.example.seungchang.global.code.ErrorCode;
import com.example.seungchang.global.exception.RestaurantNotFoundException;
import com.example.seungchang.global.responseTemplate.ApiResponseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseTemplate<Void>> handleValidationException(MethodArgumentNotValidException e) {
        log.warn("Validation error: {}", e.getMessage());
        return ApiResponseTemplate.error(ErrorCode.VALIDATION_EXCEPTION);
    }

    // 잘못된 인자 (IllegalArgumentException)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseTemplate<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("Illegal argument: {}", e.getMessage());
        return ApiResponseTemplate.error(ErrorCode.VALIDATION_EXCEPTION);
    }

    // 특정 엔티티를 찾을 수 없음 (예: Restaurant 없음)
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ApiResponseTemplate<Void>> handleCartNotFoundException(RestaurantNotFoundException e) {
        log.warn("Cart not found: {}", e.getMessage());
        return ApiResponseTemplate.error(ErrorCode.NOT_FOUND_RESTAURANT_EXCEPTION);
    }

    // 그 외 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseTemplate<Void>> handleException(Exception e) {
        log.error("Unexpected error", e);
        return ApiResponseTemplate.error(ErrorCode.INTERNAL_SERVER_EXCEPTION);
    }
}
