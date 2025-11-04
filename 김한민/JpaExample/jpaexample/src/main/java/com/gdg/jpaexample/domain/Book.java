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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false)
    private int publishedYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Book(String title, int publishedYear, Author author) {
        this.title = title;
        this.publishedYear = publishedYear;
        this.author = author;
    }

    /** 기본 정보만 변경 */
    public void updateInfo(String title, int publishedYear) {
        this.title = title;
        this.publishedYear = publishedYear;
    }

    /** 저자 변경(양방향 동기화 포함) */
    public void changeAuthor(Author newAuthor) {
        if (this.author == newAuthor) return;

        if (this.author != null) {
            this.author.removeBook(this);   // 기존 저자 컬렉션에서 제거(반대편 동기화)
        }
        this.author = newAuthor;
        if (newAuthor != null) {
            newAuthor.addBook(this);        // 새 저자 컬렉션에 추가(반대편 동기화)
        }
    }

    /* ===== Author 편의메서드에서만 쓰는 내부 동기화 메서드 ===== */
    void setAuthorOnly(Author author) {
        this.author = author;
    }

    void removeAuthorOnly() {
        this.author = null;
    }
}
