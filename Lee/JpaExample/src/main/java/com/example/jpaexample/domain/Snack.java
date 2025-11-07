package com.example.jpaexample.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Snack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder
    public Snack(String name, int releaseYear, Company company) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.company = company;
    }

    public void update(String name, int releaseYear, Company company) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.company = company;
    }
}
