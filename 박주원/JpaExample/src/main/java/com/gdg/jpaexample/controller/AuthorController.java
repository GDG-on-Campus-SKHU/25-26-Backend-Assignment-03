package com.gdg.jpaexample.controller;

import com.gdg.jpaexample.dto.AuthorInfoResponseDto;
import com.gdg.jpaexample.dto.AuthorSaveRequestDto;
import com.gdg.jpaexample.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorInfoResponseDto> saveAuthor(@RequestBody AuthorSaveRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.saveAuthor(dto));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.noContent().build();
    }
}
