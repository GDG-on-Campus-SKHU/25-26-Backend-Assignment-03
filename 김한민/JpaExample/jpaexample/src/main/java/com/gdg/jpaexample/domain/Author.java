package com.gdg.jpaexample.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String nationality;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    @Builder
    public Author(String name, String nationality, List<Book> books) {
        this.name = name;
        this.nationality = nationality;
        // 빌더로 생성 시 books를 세팅하지 않으면 null이 될 수 있으니 기본값 보장
        this.books = (books != null) ? books : new ArrayList<>();
    }

    // 양방향 편의 메서드(선택)
    public void addBook(Book book) {
        this.books.add(book);
        book.setAuthor(this);
    }
}
