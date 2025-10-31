package com.example.jpawork.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Exercise_name {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_area_id")
    private Exercise_area exerciseArea;

    @Builder
    public Exercise_name(String title, Exercise_area exerciseArea) {
        this.title = title;
        this.exerciseArea = exerciseArea;
    }

    public void update(String title, Exercise_area exerciseArea) {
        this.title = title;
        this.exerciseArea = exerciseArea;
    }
}
