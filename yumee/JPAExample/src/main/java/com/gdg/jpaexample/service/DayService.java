package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Day;
import com.gdg.jpaexample.dto.daydto.DayInfoResponseDto;
import com.gdg.jpaexample.dto.daydto.DaySaveRequestDto;
import com.gdg.jpaexample.repository.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;

    @Transactional
    public DayInfoResponseDto saveDay(DaySaveRequestDto  daySaveRequestDto) {
        Day day = Day.builder()
                .day(daySaveRequestDto.getDay())
                .month(daySaveRequestDto.getMonth())
                .build();

        dayRepository.save(day);

        return DayInfoResponseDto.from(day);
    }

    @Transactional
    public void deleteDay(Long dayId) {
        dayRepository.deleteById(dayId);
    }

    @Transactional(readOnly = true)
    public List<DayInfoResponseDto> getAllDay() {
        return dayRepository.findAll()
                .stream()
                .map(DayInfoResponseDto::from)
                .toList();
    }
}
