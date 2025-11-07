package com.example.seungchang.app.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String restaurantName;
    private String bossName;
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Food> foodList = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE) // Builder는 외부에서 직접 호출 못 하게
    private Restaurant(String restaurantName, String bossName) {
        this.restaurantName = restaurantName;
        this.bossName = bossName;
    }

    // 팩토리 메서드 (정적 생성자)
    public static Restaurant create(String restaurantName, String bossName) {
        validate(restaurantName, bossName); // 유효성 검증
        return new Restaurant(restaurantName, bossName);
    }

    private static void validate(String restaurantName, String bossName) {
        if (restaurantName == null || restaurantName.isBlank())
            throw new IllegalArgumentException("식당 이름은 비어 있을 수 없습니다.");
        if (bossName == null || bossName.isBlank())
            throw new IllegalArgumentException("사장 이름은 비어 있을 수 없습니다.");
    }
    public void update(String restaurantName, String bossName){
        this.restaurantName = restaurantName;
        this.bossName = bossName;
    }
}
