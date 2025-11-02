package com.example.jpaexample.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "founding_year")
    private int foundingYear;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Snack> snacks = new ArrayList<>();

    @Builder
    public Company(String name, int foundingYear) {
        this.name = name;
        this.foundingYear = foundingYear;
    }
}
