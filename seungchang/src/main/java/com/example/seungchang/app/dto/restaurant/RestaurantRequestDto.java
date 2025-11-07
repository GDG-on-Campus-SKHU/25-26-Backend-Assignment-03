package com.example.seungchang.app.dto.restaurant;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RestaurantRequestDto {
    @NotBlank(message = "매장명은 필수입니다")
    private String restaurantName;

    @NotBlank(message = "대표명은 필수입니다")
    private String bossName;
}
