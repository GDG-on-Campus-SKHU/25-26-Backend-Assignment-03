package com.gdg.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String work;

    @ManyToOne(fetch = FetchType.LAZY) //여러 '세부 일정'이 하나의 Soon(급한 일정)에 포함될 수 있음
    @JoinColumn(name = "urgentWork_id") //참조할 외래키 이름
    private UrgentWork urgentWork;

    @Builder
    public ToDo(String work, UrgentWork urgentWork) {
        this.work = work;
        this.urgentWork = urgentWork;
    }

    public void update(String work, UrgentWork urgentWork) {
        this.work = work;
        this.urgentWork = urgentWork;
    }
}
