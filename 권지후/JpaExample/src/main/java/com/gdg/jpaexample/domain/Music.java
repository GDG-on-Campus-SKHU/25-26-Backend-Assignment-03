package com.gdg.jpaexample.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "singer_id")
    private Singer singer;

    @Builder
    public Music(String title, Singer singer) {
        this.title = title;
        this.singer = singer;
    }

    public void update(String title, Singer singer) {
        this.title = title;
        this.singer = singer;
    }
}
