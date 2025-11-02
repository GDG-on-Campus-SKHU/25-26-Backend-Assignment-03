package com.example.jpaexample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;


@Entity
@Getter
@NoArgsConstructor

public class Snack {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String s_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id")
    private Company company;

    @Builder
    public Snack(String s_name,Company company) {
        this.s_name = s_name;
        this.company = company;
    }

    public void update(String s_name, Company company) {
        this.s_name = s_name;
        this.company = company;
    }

}
