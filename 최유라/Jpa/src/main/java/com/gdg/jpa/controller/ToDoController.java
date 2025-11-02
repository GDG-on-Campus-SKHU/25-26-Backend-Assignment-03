package com.gdg.jpa.controller;

import com.gdg.jpa.dto.ToDoInfoResponseDto;
import com.gdg.jpa.dto.ToDoSaveRequestDto;
import com.gdg.jpa.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/works")
public class ToDoController {

    private final ToDoService todoService;

    @PostMapping
    public ResponseEntity<ToDoInfoResponseDto> saveWork(@RequestBody ToDoSaveRequestDto todoSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.saveWork(todoSaveRequestDto));
    }

    @GetMapping("/{workId}") //아이디로 일정 찾기
    public ResponseEntity<ToDoInfoResponseDto> getWork(@PathVariable Long workId) {
        ToDoInfoResponseDto response = todoService.getWork(workId);
        return (response == null) ? ResponseEntity.notFound().build() : ResponseEntity.status(HttpStatus.OK).body(todoService.getWork(workId));
    }

    @PatchMapping("/{workId}")
    public ResponseEntity<?> updateWork(@PathVariable Long workId,
                                        @RequestBody ToDoSaveRequestDto todoSaveRequestDto) {
        ToDoInfoResponseDto response = todoService.getWork(workId);
        return (response == null) ? ResponseEntity.notFound().build() : ResponseEntity.status(HttpStatus.OK).body(todoService.updateWork(workId, todoSaveRequestDto));
    }

    @DeleteMapping("/{workId}")
    public ResponseEntity<ToDoInfoResponseDto> deleteWork(@PathVariable Long workId) {
        todoService.deleteWork(workId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<ToDoInfoResponseDto>> getAllWork() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAllWork());
    }
}
