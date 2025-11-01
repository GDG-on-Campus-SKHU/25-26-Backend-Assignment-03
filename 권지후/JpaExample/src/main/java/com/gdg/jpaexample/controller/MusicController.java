package com.gdg.jpaexample.controller;

import com.gdg.jpaexample.dto.MusicInfoResponseDto;
import com.gdg.jpaexample.dto.MusicSaveRequestDto;
import com.gdg.jpaexample.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/musics")
public class MusicController {

    private final MusicService musicService;

    @PostMapping
    public ResponseEntity<MusicInfoResponseDto> saveMusic(@RequestBody MusicSaveRequestDto musicSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(musicService.saveMusic(musicSaveRequestDto));
    }

    @GetMapping("/{musicId}")
    public ResponseEntity<MusicInfoResponseDto> getMusic(@PathVariable Long musicId) {
        return ResponseEntity.status(HttpStatus.OK).body(musicService.getMusic(musicId));
    }

    @PatchMapping("/{musicId}")
    public ResponseEntity<?> updateMusic(@PathVariable Long musicId,
                                         @RequestBody MusicSaveRequestDto musicSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(musicService.updateMusic(musicId, musicSaveRequestDto));
    }

    @DeleteMapping("/{musicId}")
    public ResponseEntity<MusicInfoResponseDto> deleteMusic(@PathVariable Long musicId) {
        musicService.deleteMusic(musicId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<MusicInfoResponseDto>> getAllMusic() {
        return ResponseEntity.status(HttpStatus.OK).body(musicService.getAllMusic());
    }
}
