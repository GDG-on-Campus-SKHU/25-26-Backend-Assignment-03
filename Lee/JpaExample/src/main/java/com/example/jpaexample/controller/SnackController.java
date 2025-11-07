package com.example.jpaexample.controller;

import com.example.jpaexample.dto.*;
import com.example.jpaexample.service.SnackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/snacks")
public class SnackController {

    private final SnackService snackService;

    @PostMapping
    public ResponseEntity<SnackInfoResponseDto> saveSnack(@RequestBody SnackSaveRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(snackService.saveSnack(dto));
    }

    @GetMapping("/{snackId}")
    public ResponseEntity<SnackInfoResponseDto> getSnack(@PathVariable Long snackId) {
        return ResponseEntity.ok(snackService.getSnack(snackId));
    }

    @PatchMapping("/{snackId}")
    public ResponseEntity<SnackInfoResponseDto> updateSnack(@PathVariable Long snackId,
                                                            @RequestBody SnackSaveRequestDto dto) {
        return ResponseEntity.ok(snackService.updateSnack(snackId, dto));
    }

    @DeleteMapping("/{snackId}")
    public ResponseEntity<Void> deleteSnack(@PathVariable Long snackId) {
        snackService.deleteSnack(snackId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SnackInfoResponseDto>> getAllSnacks() {
        return ResponseEntity.ok(snackService.getAllSnacks());
    }
}
