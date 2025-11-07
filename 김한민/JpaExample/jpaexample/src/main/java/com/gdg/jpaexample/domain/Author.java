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

@Entity
@Table(name = "author")
@Getter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String nationality;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    @Builder
    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    // 연관관계 편의 메서드
    public void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
            book.setAuthorOnly(this); // Book 측만 세팅(중복 방지)
        }
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            book.removeAuthorOnly(); // Book 측만 해제
        }
    }

    public void updateBasicInfo(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }
}
