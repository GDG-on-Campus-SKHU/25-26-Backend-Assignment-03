package com.example.jpawork.service;

import com.example.jpawork.domain.Exercise_area;
import com.example.jpawork.domain.Exercise_name;
import com.example.jpawork.dto.Exercise_name_InfoResponseDto;
import com.example.jpawork.dto.Exercise_name_SaveRequestDto;
import com.example.jpawork.repository.Exercise_area_Repository;
import com.example.jpawork.repository.Exercise_name_Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Exercise_name_Service {

    private final Exercise_name_Repository exerciseNameRepository;
    private final Exercise_area_Repository exerciseAreaRepository;

    @Transactional
    public Exercise_name_InfoResponseDto saveExerciseName(Exercise_name_SaveRequestDto dto) {
        Exercise_area area = exerciseAreaRepository.findById(dto.getExerciseAreaId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운동 부위입니다."));

        Exercise_name exerciseName = Exercise_name.builder()
                .title(dto.getTitle())
                .exerciseArea(area)
                .build();

        exerciseNameRepository.save(exerciseName);
        return Exercise_name_InfoResponseDto.from(exerciseName);
    }

    @Transactional(readOnly = true)
    public Exercise_name_InfoResponseDto getExerciseName(Long id) {
        Exercise_name exerciseName = exerciseNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 운동 정보를 찾을 수 없습니다."));
        return Exercise_name_InfoResponseDto.from(exerciseName);
    }

    @Transactional
    public Exercise_name_InfoResponseDto updateExerciseName(Long id, Exercise_name_SaveRequestDto dto) {
        Exercise_name exerciseName = exerciseNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 운동 정보를 찾을 수 없습니다."));

        Exercise_area area = exerciseAreaRepository.findById(dto.getExerciseAreaId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 운동 부위입니다."));

        exerciseName.update(dto.getTitle(), area);
        return Exercise_name_InfoResponseDto.from(exerciseName);
    }

    @Transactional
    public void deleteExerciseName(Long id) {
        exerciseNameRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Exercise_name_InfoResponseDto> getAllExerciseNames() {
        return exerciseNameRepository.findAll()
                .stream()
                .map(Exercise_name_InfoResponseDto::from)
                .toList();
    }
}
