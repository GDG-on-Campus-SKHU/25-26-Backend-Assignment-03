package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Author;
import com.gdg.jpaexample.domain.Book;
import com.gdg.jpaexample.dto.BookInfoResponseDto;
import com.gdg.jpaexample.dto.BookSaveRequestDto;
import com.gdg.jpaexample.repository.AuthorRepository;
import com.gdg.jpaexample.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public BookInfoResponseDto save(BookSaveRequestDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 저자입니다."));
        Book book = Book.builder()
                .title(dto.getTitle())
                .publishedYear(dto.getPublishedYear())
                .author(author)
                .build();
        return BookInfoResponseDto.from(bookRepository.save(book));
    }

    @Transactional(readOnly = true)
    public List<BookInfoResponseDto> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookInfoResponseDto::from)
                .toList();
    }

    @Transactional
    public BookInfoResponseDto update(Long id, BookSaveRequestDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("도서를 찾을 수 없습니다."));
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 저자입니다."));
        book.update(dto.getTitle(), dto.getPublishedYear(), author);
        return BookInfoResponseDto.from(book);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
