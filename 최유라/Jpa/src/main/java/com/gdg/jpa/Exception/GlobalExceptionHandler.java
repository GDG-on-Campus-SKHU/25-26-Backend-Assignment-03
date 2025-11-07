package com.gdg.jpa.Exception;

import com.gdg.jpa.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler { //예외 처리 담당 클래스

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponse response = new ErrorResponse(
                "INVALID_ARGUMENT",
                e.getMessage()
        );

        return ResponseEntity.badRequest().body(response);
    }
}
