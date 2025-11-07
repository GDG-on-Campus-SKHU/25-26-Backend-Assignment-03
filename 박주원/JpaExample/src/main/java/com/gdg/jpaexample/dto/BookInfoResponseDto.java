package com.gdg.jpaexample.dto;

import com.gdg.jpaexample.domain.Book;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookInfoResponseDto {
    private Long id;
    private String title;
    private Long authorId;
    private String authorName;

    public static BookInfoResponseDto from(Book book) {
        return BookInfoResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorId(book.getAuthor().getId())
                .authorName(book.getAuthor().getName())
                .build();
    }
}
