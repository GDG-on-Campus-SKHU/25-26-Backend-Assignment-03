package com.gdg.businfo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;
    private String startPoint;
    private String endPoint;

    @Builder
    public Route(String routeName, String startPoint, String endPoint) {
        this.routeName = routeName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Bus> buses = new ArrayList<>();

    public void update(String routeName, String startPoint, String endPoint) {
        this.routeName = routeName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

}
