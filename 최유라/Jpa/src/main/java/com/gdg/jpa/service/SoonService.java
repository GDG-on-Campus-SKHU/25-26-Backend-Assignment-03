package com.gdg.jpa.service;

import com.gdg.jpa.domain.Soon;
import com.gdg.jpa.dto.SoonInfoResponseDto;
import com.gdg.jpa.dto.SoonSaveRequestDto;
import com.gdg.jpa.repository.SoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoonService {
    private final SoonRepository soonRepository;

    @Transactional
    public SoonInfoResponseDto saveSoon(SoonSaveRequestDto soonSaveRequestDto) {
        Soon soon = Soon.builder()
                .first(soonSaveRequestDto.getFirst())
                .limit(soonSaveRequestDto.getLimit())
                .build();

        soonRepository.save(soon);

        return SoonInfoResponseDto.from(soon);
    }

    @Transactional
    public void deleteSoon(Long soonId) {
        soonRepository.deleteById(soonId);
    }

    @Transactional(readOnly = true)
    public List<SoonInfoResponseDto> getAllWork() { //전체 일정 조회
        return soonRepository.findAll()
                .stream()
                .map(SoonInfoResponseDto::from)
                .sorted(Comparator.comparing(SoonInfoResponseDto::getLimit))
                .collect(Collectors.toList());
    } //남은 일수 기준 오름차순 정렬해 리스트로 만듦
}
