package com.gdg.jpaexample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(name = "published_year", nullable = false)
    private int publishedYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Builder
    public Book(String title, int publishedYear, Author author) {
        this.title = title;
        this.publishedYear = publishedYear;
        this.author = author;
    }

    public void update(String title, int publishedYear, Author author) {
        this.title = title;
        this.publishedYear = publishedYear;
        this.author = author;
    }

    // Author.addBook에서 호출
    void setAuthor(Author author) {
        this.author = author;
    }
}
