package com.gdg.jpa.controller;

import com.gdg.jpa.dto.SoonInfoResponseDto;
import com.gdg.jpa.dto.SoonSaveRequestDto;
import com.gdg.jpa.service.SoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/soons")
public class SoonController {

    private final SoonService soonService;

    @PostMapping //입력한 값 숨겨진 채로 전달됨(@GetMapping은 도메인에? 값이 보임)
    public ResponseEntity<SoonInfoResponseDto> saveSoon(@RequestBody SoonSaveRequestDto soonSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(soonService.saveSoon(soonSaveRequestDto));
    } //급한 일정 단순 추가 메서드

    @DeleteMapping("/{soonId}")
    public ResponseEntity<SoonInfoResponseDto> deleteSoonById(@PathVariable Long soonId) {
        soonService.deleteSoon(soonId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //삭제 성공 시
    } //아이디로 급한 일정 삭제 메서드

    @GetMapping
    public ResponseEntity<List<SoonInfoResponseDto>> getAllWork() {
        return ResponseEntity.status(HttpStatus.OK).body(soonService.getAllWork());
    }
}
