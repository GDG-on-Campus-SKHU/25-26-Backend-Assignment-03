package com.gdg.jpaexample.controller;

import com.gdg.jpaexample.dto.ProfessorInfoResponseDto;
import com.gdg.jpaexample.dto.ProfessorSaveRequestDto;
import com.gdg.jpaexample.service.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorInfoResponseDto> saveProfessor(@Valid @RequestBody ProfessorSaveRequestDto professorSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.saveProfessor(professorSaveRequestDto));
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<ProfessorInfoResponseDto> getProfessor(@PathVariable Long professorId) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getProfessor(professorId));
    }

    @PatchMapping("/{professorId}")
    public ResponseEntity<ProfessorInfoResponseDto> updateProfessor(@PathVariable Long professorId,
                                         @Valid @RequestBody ProfessorSaveRequestDto professorSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.updateProfessor(professorId, professorSaveRequestDto));
    }

    @DeleteMapping("/{professorId}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long professorId) {
        professorService.deleteProfessor(professorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<ProfessorInfoResponseDto>> getAllProfessor() {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getAllProfessor());
    }
}
