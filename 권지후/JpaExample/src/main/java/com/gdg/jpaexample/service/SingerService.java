package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Singer;
import com.gdg.jpaexample.dto.SingerInfoResponseDto;
import com.gdg.jpaexample.dto.SingerSaveRequestDto;
import com.gdg.jpaexample.repository.SingerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SingerService {

    private final SingerRepository singerRepository;

    @Transactional
    public SingerInfoResponseDto saveSinger(SingerSaveRequestDto singerSaveRequestDto) {
        Singer singer = Singer.builder()
                .name(singerSaveRequestDto.getName())
                .debutYear(singerSaveRequestDto.getDebutYear())
                .build();

        singerRepository.save(singer);

        return SingerInfoResponseDto.from(singer);
    }

    @Transactional
    public void deleteSinger(Long singerId) {
        singerRepository.deleteById(singerId);
    }
}
