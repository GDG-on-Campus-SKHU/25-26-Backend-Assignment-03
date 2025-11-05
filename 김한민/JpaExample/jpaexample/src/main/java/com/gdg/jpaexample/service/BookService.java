package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Author;
import com.gdg.jpaexample.domain.Book;
import com.gdg.jpaexample.dto.BookChangeAuthorRequestDto;
import com.gdg.jpaexample.dto.BookInfoResponseDto;
import com.gdg.jpaexample.dto.BookSaveRequestDto;
import com.gdg.jpaexample.dto.BookUpdateInfoRequestDto;
import com.gdg.jpaexample.repository.AuthorRepository;
import com.gdg.jpaexample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List; // <-- 추가

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public BookInfoResponseDto save(BookSaveRequestDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 저자입니다."));
        Book book = new Book(dto.getTitle(), dto.getPublishedYear(), author);
        author.addBook(book); // 양방향 동기화
        bookRepository.save(book);
        return BookInfoResponseDto.from(book);
    }

    // 컨트롤러에서 호출하는 목록 조회 메서드
    @Transactional(readOnly = true)
    public List<BookInfoResponseDto> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookInfoResponseDto::from)
                .toList();
    }

    @Transactional
    public BookInfoResponseDto updateInfo(Long bookId, BookUpdateInfoRequestDto dto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 도서를 찾을 수 없습니다."));
        book.updateInfo(dto.getTitle(), dto.getPublishedYear());
        return BookInfoResponseDto.from(book);
    }

    @Transactional
    public BookInfoResponseDto changeAuthor(Long bookId, BookChangeAuthorRequestDto dto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 도서를 찾을 수 없습니다."));
        Author newAuthor = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 저자입니다."));
        book.changeAuthor(newAuthor); // 양방향 동기화
        return BookInfoResponseDto.from(book);
    }

    @Transactional
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
