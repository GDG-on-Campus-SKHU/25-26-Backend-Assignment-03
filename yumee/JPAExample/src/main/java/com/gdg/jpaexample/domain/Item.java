package com.gdg.jpaexample.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Integer price;
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id")
    private Day day;

    @Builder
    public Item(String title, Integer price, Integer amount, Day day) {
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.day = day;
    }

    public void update(String title, Integer price, Integer amount, Day day) {
        this.title = title;
        this.price = price;
        this.amount = amount;
        this.day = day;
    }
}
