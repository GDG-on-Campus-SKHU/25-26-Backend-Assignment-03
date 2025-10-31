package com.example.jpawork.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Exercise_area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int countWeek;

    @OneToMany(mappedBy = "exerciseArea", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise_name> exerciseNames = new ArrayList<>();

    @Builder
    public Exercise_area(String name, int countWeek) {
        this.name = name;
        this.countWeek = countWeek;
    }
}
