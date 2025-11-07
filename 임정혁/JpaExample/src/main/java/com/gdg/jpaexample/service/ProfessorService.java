package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Major;
import com.gdg.jpaexample.domain.Professor;
import com.gdg.jpaexample.dto.ProfessorInfoResponseDto;
import com.gdg.jpaexample.dto.ProfessorSaveRequestDto;
import com.gdg.jpaexample.repository.MajorRepository;
import com.gdg.jpaexample.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final MajorRepository majorRepository;

    @Transactional
    public ProfessorInfoResponseDto saveProfessor(ProfessorSaveRequestDto professorSaveRequestDto) {
        Major major = majorRepository.findById(professorSaveRequestDto.getMajorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 전공입니다."));

        Professor professor = Professor.builder()
                .name(professorSaveRequestDto.getName())
                .email(professorSaveRequestDto.getEmail())
                .build();

        // 양방향 연관관계 동기화
        professor.changeMajor(major);

        professorRepository.save(professor);

        return ProfessorInfoResponseDto.from(professor);
    }

    @Transactional(readOnly = true)
    public ProfessorInfoResponseDto getProfessor(Long professorId) {
        Professor professor = professorRepository.findByIdWithMajor(professorId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 교수 정보를 찾을 수 없습니다."));

        return ProfessorInfoResponseDto.from(professor);
    }

    @Transactional
    public ProfessorInfoResponseDto updateProfessor(Long professorId, ProfessorSaveRequestDto professorSaveRequestDto) {
        Professor professor = professorRepository.findByIdWithMajor(professorId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 교수 정보를 찾을 수 없습니다."));

        // null이 아닌 필드만 업데이트 (PATCH 방식)
        if (professorSaveRequestDto.getName() != null) {
            professor.updateName(professorSaveRequestDto.getName());
        }

        if (professorSaveRequestDto.getEmail() != null) {
            professor.updateEmail(professorSaveRequestDto.getEmail());
        }

        if (professorSaveRequestDto.getMajorId() != null) {
            Major major = majorRepository.findById(professorSaveRequestDto.getMajorId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 전공입니다."));
            // 양방향 연관관계 동기화
            professor.changeMajor(major);
        }

        return ProfessorInfoResponseDto.from(professor);
    }

    @Transactional
    public void deleteProfessor(Long professorId) {
        Professor professor = professorRepository.findByIdWithMajor(professorId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 교수 정보를 찾을 수 없습니다."));
        
        professorRepository.delete(professor);
    }

    @Transactional(readOnly = true)
    public List<ProfessorInfoResponseDto> getAllProfessor() {
        return professorRepository.findAllWithMajor()
                .stream()
                .map(ProfessorInfoResponseDto::from)
                .toList();
    }
}

