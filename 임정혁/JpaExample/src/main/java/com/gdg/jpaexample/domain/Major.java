package com.gdg.jpaexample.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "major")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "major", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Professor> professors = new ArrayList<>();

    @Builder
    public Major(String name) {
        this.name = name;
    }

    public void updateName(String name) {
        this.name = name;
    }

    /**
     * 양방향 연관관계 동기화를 위한 편의 메서드
     * Major에 Professor를 추가하면서 Professor의 major도 동기화
     */
    public void addProfessor(Professor professor) {
        if (!this.professors.contains(professor)) {
            this.professors.add(professor);
        }
        if (professor.getMajor() != this) {
            professor.changeMajor(this);
        }
    }

    /**
     * 양방향 연관관계 동기화를 위한 편의 메서드
     * Major에서 Professor를 제거하면서 Professor의 major도 null로 설정
     */
    public void removeProfessor(Professor professor) {
        this.professors.remove(professor);
        if (professor.getMajor() == this) {
            professor.changeMajor(null);
        }
    }
}
