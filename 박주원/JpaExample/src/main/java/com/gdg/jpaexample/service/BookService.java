package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Author;
import com.gdg.jpaexample.domain.Book;
import com.gdg.jpaexample.dto.BookInfoResponseDto;
import com.gdg.jpaexample.dto.BookSaveRequestDto;
import com.gdg.jpaexample.repository.AuthorRepository;
import com.gdg.jpaexample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public BookInfoResponseDto saveBook(BookSaveRequestDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 저자입니다."));

        Book book = Book.create(dto.getTitle(), author);
        bookRepository.save(book);

        return BookInfoResponseDto.from(book);
    }

    @Transactional(readOnly = true)
    public BookInfoResponseDto getBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 책 정보를 찾을 수 없습니다."));
        return BookInfoResponseDto.from(book);
    }

    @Transactional
    public BookInfoResponseDto updateBook(Long bookId, BookSaveRequestDto dto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 책 정보를 찾을 수 없습니다."));

        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 저자입니다."));

        book.update(dto.getTitle(), author);
        return BookInfoResponseDto.from(book);
    }

    @Transactional
    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 책 정보를 찾을 수 없습니다."));

        bookRepository.delete(book);

    }

    @Transactional(readOnly = true)
    public List<BookInfoResponseDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookInfoResponseDto::from)
                .toList();
    }
}
