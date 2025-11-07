package com.gdg.jpaexample.dto;

import com.gdg.jpaexample.domain.Book;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookInfoResponseDto {
    private Long id;
    private String title;
    private int publishedYear;
    private Long authorId;
    private String authorName;

    public static BookInfoResponseDto from(Book book) {
        return BookInfoResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .publishedYear(book.getPublishedYear())
                .authorId(book.getAuthor().getId())
                .authorName(book.getAuthor().getName())
                .build();
    }
}
