package com.gdg.jpa.controller;

import com.gdg.jpa.dto.UrgentWorkInfoResponseDto;
import com.gdg.jpa.dto.UrgentWorkSaveRequestDto;
import com.gdg.jpa.service.UrgentWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/urgentWorks")
public class UrgentWorkController {

    private final UrgentWorkService urgentWorkService;

    @PostMapping //입력한 값 숨겨진 채로 전달됨(@GetMapping은 도메인에? 값이 보임)
    public ResponseEntity<UrgentWorkInfoResponseDto> saveUrgentWork(@RequestBody UrgentWorkSaveRequestDto urgentWorkSaveRequestDto) {
        return ResponseEntity.created(URI.create("/urgentWorks/")).body(urgentWorkService.saveUrgentWork(urgentWorkSaveRequestDto));
    } //급한 일정 단순 추가 메서드

    @DeleteMapping("/{urgentWorkId}")
    public ResponseEntity<UrgentWorkInfoResponseDto> deleteUrgentWorkById(@PathVariable Long urgentWorkId) {
        urgentWorkService.deleteUrgentWork(urgentWorkId);
        return ResponseEntity.noContent().build(); //삭제 성공 시
    } //아이디로 급한 일정 삭제 메서드

    @GetMapping
    public ResponseEntity<List<UrgentWorkInfoResponseDto>> getAllUrgentWork() {
        return ResponseEntity.ok(urgentWorkService.getAllUrgentWork());
        //return ResponseEntity.status(HttpStatus.OK).body(soonService.getAllWork());
    }
}
