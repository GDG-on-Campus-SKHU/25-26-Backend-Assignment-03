package com.example.jpawork.controller;

import com.example.jpawork.dto.Exercise_name_InfoResponseDto;
import com.example.jpawork.dto.Exercise_name_SaveRequestDto;
import com.example.jpawork.service.Exercise_name_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise-name")
public class Exercise_name_Controller {

    private final Exercise_name_Service exerciseNameService;

    @PostMapping
    public ResponseEntity<Exercise_name_InfoResponseDto> saveExerciseName(
            @RequestBody Exercise_name_SaveRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(exerciseNameService.saveExerciseName(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise_name_InfoResponseDto> getExerciseName(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(exerciseNameService.getExerciseName(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Exercise_name_InfoResponseDto> updateExerciseName(
            @PathVariable Long id,
            @RequestBody Exercise_name_SaveRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(exerciseNameService.updateExerciseName(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExerciseName(@PathVariable Long id) {
        exerciseNameService.deleteExerciseName(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<Exercise_name_InfoResponseDto>> getAllExerciseNames() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(exerciseNameService.getAllExerciseNames());
    }
}
