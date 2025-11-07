package com.example.jpawork.service;

import com.example.jpawork.domain.Exercise_area;
import com.example.jpawork.dto.Exercise_area_InfoResponseDto;
import com.example.jpawork.dto.Exercise_area_SaveRequestDto;
import com.example.jpawork.repository.Exercise_area_Repository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Exercise_area_Service {

    private final Exercise_area_Repository exerciseAreaRepository;

    @Transactional
    public Exercise_area_InfoResponseDto saveExerciseArea(Exercise_area_SaveRequestDto dto) {
        Exercise_area area = Exercise_area.builder()
                .name(dto.getName())
                .countWeek(dto.getCountWeek())
                .build();

        exerciseAreaRepository.save(area);
        return Exercise_area_InfoResponseDto.from(area);
    }

    @Transactional
    public void deleteExerciseArea(Long id) {
        exerciseAreaRepository.deleteById(id);
    }
}
