package com.example.jpaexample.controller;

import com.example.jpaexample.dto.SnackInfoResponseDto;
import com.example.jpaexample.dto.SnackSaveRequestDto;
import com.example.jpaexample.service.SnackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/snacks")
public class SnackController {

    private final SnackService snackService;

    @PostMapping
    public ResponseEntity<SnackInfoResponseDto> saveSnack(@RequestBody SnackSaveRequestDto snackSaveRequestDto) {
        SnackInfoResponseDto savedSnack = snackService.saveSnack(snackSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSnack);
    }

    @GetMapping("/{snackId}")
    public ResponseEntity<SnackInfoResponseDto> getSnack(@PathVariable Long snackId) {
        SnackInfoResponseDto snackInfo = snackService.getSnack(snackId);
        return ResponseEntity.status(HttpStatus.OK).body(snackInfo);
    }

    @PatchMapping("/{snackId}")
    public ResponseEntity<SnackInfoResponseDto> updateSnack(
            @PathVariable Long snackId,
            @RequestBody SnackSaveRequestDto snackSaveRequestDto) {
        SnackInfoResponseDto updatedSnack = snackService.updateSnack(snackId, snackSaveRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSnack);
    }

    @DeleteMapping("/{snackId}")
    public ResponseEntity<Void> deleteSnack(@PathVariable Long snackId) {
        snackService.deleteSnack(snackId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<SnackInfoResponseDto>> getAllSnacks() {
        List<SnackInfoResponseDto> snacks = snackService.getAllSnacks();
        return ResponseEntity.status(HttpStatus.OK).body(snacks);
    }
}
