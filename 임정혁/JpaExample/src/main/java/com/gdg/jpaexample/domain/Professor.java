package com.gdg.jpaexample.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professor")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @Builder
    public Professor(String name, String email, Major major) {
        this.name = name;
        this.email = email;
        this.major = major;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    /**
     * 양방향 연관관계 동기화를 위한 편의 메서드
     * Professor의 major를 변경하면서 Major의 professors 컬렉션도 동기화
     */
    public void changeMajor(Major newMajor) {
        // 기존 major와의 관계 제거
        if (this.major != null) {
            this.major.getProfessors().remove(this);
        }
        
        // 새로운 major 설정
        this.major = newMajor;
        
        // 새 major의 컬렉션에 추가
        if (newMajor != null && !newMajor.getProfessors().contains(this)) {
            newMajor.getProfessors().add(this);
        }
    }
}

