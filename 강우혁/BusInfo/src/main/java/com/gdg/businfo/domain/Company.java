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
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String phoneNumber;
    private String region;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bus> buses = new ArrayList<>();

    @Builder
    public Company(String companyName, String phoneNumber, String region) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.region = region;
    }
    public void update(String companyName, String phoneNumber, String region) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.region = region;
    }
}
