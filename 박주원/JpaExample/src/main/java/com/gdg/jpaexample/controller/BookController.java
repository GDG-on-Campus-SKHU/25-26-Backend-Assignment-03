package com.gdg.jpaexample.controller;

import com.gdg.jpaexample.dto.BookInfoResponseDto;
import com.gdg.jpaexample.dto.BookSaveRequestDto;
import com.gdg.jpaexample.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookInfoResponseDto> saveBook(@RequestBody BookSaveRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(dto));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookInfoResponseDto> getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<BookInfoResponseDto> updateBook(@PathVariable Long bookId,
                                                          @RequestBody BookSaveRequestDto dto) {
        return ResponseEntity.ok(bookService.updateBook(bookId, dto));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BookInfoResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
