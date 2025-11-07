package com.gdg.jpaexample.controller;

import com.gdg.jpaexample.dto.BookChangeAuthorRequestDto;
import com.gdg.jpaexample.dto.BookInfoResponseDto;
import com.gdg.jpaexample.dto.BookSaveRequestDto;
import com.gdg.jpaexample.dto.BookUpdateInfoRequestDto;
import com.gdg.jpaexample.service.BookService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookInfoResponseDto save(@RequestBody BookSaveRequestDto dto) {
        return bookService.save(dto);
    }

    @GetMapping
    public List<BookInfoResponseDto> getAll() {
        return bookService.getAll();
    }

    @PatchMapping("/{id}")
    public BookInfoResponseDto update(@PathVariable Long id, @RequestBody BookUpdateInfoRequestDto dto) {
        return bookService.updateInfo(id, dto);
    }

    @PatchMapping("/{id}/author")
    public BookInfoResponseDto changeAuthor(@PathVariable Long id, @RequestBody BookChangeAuthorRequestDto dto) {
        return bookService.changeAuthor(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
