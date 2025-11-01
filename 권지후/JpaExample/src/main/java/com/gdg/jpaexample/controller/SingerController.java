package com.gdg.jpaexample.controller;

import com.gdg.jpaexample.dto.SingerInfoResponseDto;
import com.gdg.jpaexample.dto.SingerSaveRequestDto;
import com.gdg.jpaexample.service.SingerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/singers")
public class SingerController {

    private final SingerService singerService;

    @PostMapping
    public ResponseEntity<SingerInfoResponseDto> saveSinger(@RequestBody SingerSaveRequestDto singerSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(singerService.saveSinger(singerSaveRequestDto));
    }

    @DeleteMapping("/{singerId}")
    public ResponseEntity<SingerInfoResponseDto> deleteSingerById(@PathVariable Long singerId) {
        singerService.deleteSinger(singerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
