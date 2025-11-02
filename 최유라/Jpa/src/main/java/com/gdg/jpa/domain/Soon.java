package com.gdg.jpa.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Soon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id 생성을 DB에 완전히 맡김
    private Long id; //DB가 생성해주는 id?
    private String first; //클라이언트?가 입력해야 함

    @Column(name = "limit_time") //DB에서 실제로 사용되는 컬럼 이름
    private int limit;
    //Soon(급한 일정)에 여러 '세부 일정'이 포함될 수 있음
    @OneToMany(mappedBy = "soon", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDo> todos = new ArrayList<>();

    @Builder
    public Soon(String first, int limit) {
        this.first = first;
        this.limit = limit;
    }
}
