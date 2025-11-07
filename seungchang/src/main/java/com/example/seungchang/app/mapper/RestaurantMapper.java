package com.example.seungchang.app.mapper;

import com.example.seungchang.app.domain.Restaurant;
import com.example.seungchang.app.dto.food.FoodResponseDto;
import com.example.seungchang.app.dto.restaurant.RestaurantRequestDto;
import com.example.seungchang.app.dto.restaurant.RestaurantResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public Restaurant toEntity(RestaurantRequestDto restaurantRequestDto){
        return Restaurant.create(restaurantRequestDto.getRestaurantName(), restaurantRequestDto.getBossName());
    }

    public RestaurantResponseDto toDto(Restaurant restaurant){
        return RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .restaurantName(restaurant.getRestaurantName())
                .bossName(restaurant.getBossName())
                .countOfFood(restaurant.getFoodList().size())
                .foodList(
                        restaurant.getFoodList().stream()
                                .map(food -> FoodResponseDto.builder()
                                        .id(food.getId())
                                        .foodName(food.getFoodName())
                                        .kcal(food.getKcal())
                                        .amountOfSelling(food.getAmountOfSelling())
                                        .restaurantId(restaurant.getId())
                                        .restaurantName(restaurant.getRestaurantName())
                                        .build()
                                ).toList()
                )
                .build();
    }
}
