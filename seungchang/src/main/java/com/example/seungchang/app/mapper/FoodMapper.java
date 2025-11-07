package com.example.seungchang.app.mapper;

import com.example.seungchang.app.domain.Food;
import com.example.seungchang.app.domain.Restaurant;
import com.example.seungchang.app.dto.food.FoodRequestDto;
import com.example.seungchang.app.dto.food.FoodResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FoodMapper {
    public Food toEntity(FoodRequestDto foodRequestDto, Restaurant restaurant){
        return Food.createFood(foodRequestDto.getFoodName(), foodRequestDto.getKcal(), restaurant);
    }

    public FoodResponseDto toDto(Food food){
        return FoodResponseDto.builder()
                .id(food.getId())
                .foodName(food.getFoodName())
                .kcal(food.getKcal())
                .amountOfSelling(food.getAmountOfSelling())
                .restaurantId(food.getRestaurant().getId())
                .restaurantName(food.getRestaurant().getRestaurantName())
                .build();
    }
}
