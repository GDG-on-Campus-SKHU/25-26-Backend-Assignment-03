package com.gdg.businfo.domain;

import jakarta.persistence.CascadeType;
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
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Route route;

    @Builder
    public Bus(String busNumber, String type, Company company, Route route) {
        this.busNumber = busNumber;
        this.type = type;
        this.company = company;
        this.route = route;
    }
    public void update (String busNumber, String type){
        this.busNumber = busNumber;
        this.type = type;
    }
    public void updateCompanyBus(String busNumber, String type, Company company) {
        this.busNumber = busNumber;
        this.type = type;
        this.company = company;
    }
    public void updateRouteBus(String busNumber, String type, Route route) {
        this.busNumber = busNumber;
        this.type = type;
        this.route = route;
    }
    public void updateAllBus(String busNumber, String type, Company company, Route route) {
        this.busNumber = busNumber;
        this.type = type;
        this.company = company;
        this.route = route;
    }
}
