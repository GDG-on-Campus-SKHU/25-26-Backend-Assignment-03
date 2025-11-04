package com.gdg.jpa.service;

import com.gdg.jpa.domain.UrgentWork;
import com.gdg.jpa.dto.UrgentWorkInfoResponseDto;
import com.gdg.jpa.dto.UrgentWorkSaveRequestDto;
import com.gdg.jpa.repository.UrgentWorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UrgentWorkService {
    private final UrgentWorkRepository urgentWorkRepository;

    @Transactional
    public UrgentWorkInfoResponseDto saveUrgentWork(UrgentWorkSaveRequestDto urgentWorkSaveRequestDto) {
        UrgentWork urgentWork = UrgentWork.builder()
                .schedule(urgentWorkSaveRequestDto.getSchedule())
                .daysLeft(urgentWorkSaveRequestDto.getDaysLeft())
                .build();

        urgentWorkRepository.save(urgentWork);

        return UrgentWorkInfoResponseDto.from(urgentWork);
    }

    @Transactional
    public void deleteUrgentWork(Long urgentWorkId) {
        urgentWorkRepository.deleteById(urgentWorkId);
    }

    @Transactional(readOnly = true)
    public List<UrgentWorkInfoResponseDto> getAllUrgentWork() { //전체 일정 조회
        return urgentWorkRepository.findAll()
                .stream()
                .map(UrgentWorkInfoResponseDto::from)
                .sorted(Comparator.comparing(UrgentWorkInfoResponseDto::getDaysLeft))
                .collect(Collectors.toList());
    } //남은 일수 기준 오름차순 정렬해 리스트로 만듦
}
