package com.gdg.jpaexample.controller;

import com.gdg.jpaexample.dto.AuthorInfoResponseDto;
import com.gdg.jpaexample.dto.AuthorSaveRequestDto;
import com.gdg.jpaexample.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public AuthorInfoResponseDto save(@RequestBody AuthorSaveRequestDto dto) {
        return authorService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
