package com.gdg.jpaexample.dto;

import com.gdg.jpaexample.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String category;
    private Double avgRating;
    private String createdAt;
    private String updatedAt;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    public static RestaurantResponseDto fromEntity(Restaurant restaurant) {
        return RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phone(restaurant.getPhone())
                .category(restaurant.getCategory())
                .avgRating(restaurant.getAvgRating())
                .createdAt(restaurant.getCreatedAt() != null ? restaurant.getCreatedAt().format(FORMATTER) : null)
                .updatedAt(restaurant.getUpdatedAt() != null ? restaurant.getUpdatedAt().format(FORMATTER) : null)
                .build();
    }
}
