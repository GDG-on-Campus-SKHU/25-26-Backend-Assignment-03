package com.example.jpawork.controller;

import com.example.jpawork.dto.Exercise_area_InfoResponseDto;
import com.example.jpawork.dto.Exercise_area_SaveRequestDto;
import com.example.jpawork.service.Exercise_area_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise-area")
public class Exercise_area_Controller {

    private final Exercise_area_Service exerciseAreaService;

    @PostMapping
    public ResponseEntity<Exercise_area_InfoResponseDto> saveExerciseArea(
            @RequestBody Exercise_area_SaveRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(exerciseAreaService.saveExerciseArea(requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExerciseArea(@PathVariable Long id) {
        exerciseAreaService.deleteExerciseArea(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
