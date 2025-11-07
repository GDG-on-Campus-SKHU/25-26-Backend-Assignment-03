package com.gdg.jpaexample.controller;

import com.gdg.jpaexample.dto.MajorInfoResponseDto;
import com.gdg.jpaexample.dto.MajorSaveRequestDto;
import com.gdg.jpaexample.service.MajorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/majors")
public class MajorController {

    private final MajorService majorService;

    @PostMapping
    public ResponseEntity<MajorInfoResponseDto> saveMajor(@Valid @RequestBody MajorSaveRequestDto majorSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(majorService.saveMajor(majorSaveRequestDto));
    }

    @GetMapping("/{majorId}")
    public ResponseEntity<MajorInfoResponseDto> getMajor(@PathVariable Long majorId) {
        return ResponseEntity.status(HttpStatus.OK).body(majorService.getMajor(majorId));
    }

    @GetMapping
    public ResponseEntity<List<MajorInfoResponseDto>> getAllMajors() {
        return ResponseEntity.status(HttpStatus.OK).body(majorService.getAllMajors());
    }

    @PatchMapping("/{majorId}")
    public ResponseEntity<MajorInfoResponseDto> updateMajor(@PathVariable Long majorId,
                                                             @Valid @RequestBody MajorSaveRequestDto majorSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(majorService.updateMajor(majorId, majorSaveRequestDto));
    }

    @DeleteMapping("/{majorId}")
    public ResponseEntity<Void> deleteMajorById(@PathVariable Long majorId) {
        majorService.deleteMajor(majorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

