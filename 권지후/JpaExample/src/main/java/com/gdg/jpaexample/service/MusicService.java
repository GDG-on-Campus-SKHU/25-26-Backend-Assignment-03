package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Music;
import com.gdg.jpaexample.domain.Singer;
import com.gdg.jpaexample.dto.MusicInfoResponseDto;
import com.gdg.jpaexample.dto.MusicSaveRequestDto;
import com.gdg.jpaexample.repository.MusicRepository;
import com.gdg.jpaexample.repository.SingerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;
    private final SingerRepository singerRepository;

    @Transactional
    public MusicInfoResponseDto saveMusic(MusicSaveRequestDto musicSaveRequestDto) {
        Singer singer = singerRepository.findById(musicSaveRequestDto.getSingerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가수입니다."));

        Music music = Music.builder()
                .singer(singer)
                .title(musicSaveRequestDto.getTitle())
                .build();

        musicRepository.save(music);

        return MusicInfoResponseDto.from(music);
    }

    @Transactional(readOnly = true)
    public MusicInfoResponseDto getMusic(Long musicId) {
        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 음악 정보를 찾을 수 없습니다."));

        return MusicInfoResponseDto.from(music);
    }

    @Transactional
    public MusicInfoResponseDto updateMusic(Long musicId, MusicSaveRequestDto musicSaveRequestDto) {
        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 음악 정보를 찾을 수 없습니다."));

        Singer singer = singerRepository.findById(musicSaveRequestDto.getSingerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가수입니다."));

        music.update(musicSaveRequestDto.getTitle(), singer);

        return MusicInfoResponseDto.from(music);
    }

    @Transactional
    public void deleteMusic(Long musicId) {
        musicRepository.deleteById(musicId);
    }

    @Transactional(readOnly = true)
    public List<MusicInfoResponseDto> getAllMusic() {
        return musicRepository.findAll()
                .stream()
                .map(MusicInfoResponseDto::from)
                .toList();
    }
}
