package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Restaurant;
import com.gdg.jpaexample.domain.Review;
import com.gdg.jpaexample.dto.RestaurantRequestDto;
import com.gdg.jpaexample.dto.RestaurantResponseDto;
import com.gdg.jpaexample.dto.ReviewResponseDto;
import com.gdg.jpaexample.repository.RestaurantRepository;
import com.gdg.jpaexample.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto requestDto) {
        Restaurant restaurant = Restaurant.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .phone(requestDto.getPhone())
                .category(requestDto.getCategory())
                .build();
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return RestaurantResponseDto.fromEntity(savedRestaurant);
    }

    @Transactional(readOnly = true)
    public RestaurantResponseDto getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("맛집을 찾을 수 없습니다. ID: " + id));
        return RestaurantResponseDto.fromEntity(restaurant);
    }

    @Transactional(readOnly = true)
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public RestaurantResponseDto updateRestaurant(Long id, RestaurantRequestDto requestDto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("맛집을 찾을 수 없습니다. ID: " + id));

        restaurant.update(requestDto.getName(), requestDto.getAddress(), requestDto.getPhone(), requestDto.getCategory());
        return RestaurantResponseDto.fromEntity(restaurant);
    }

    @Transactional
    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new NoSuchElementException("맛집을 찾을 수 없습니다. ID: " + id);
        }
        restaurantRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getReviewsByRestaurantId(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("맛집을 찾을 수 없습니다. ID: " + restaurantId));
        return restaurant.getReviews().stream()
                .map(ReviewResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateRestaurantAverageRating(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NoSuchElementException("맛집을 찾을 수 없습니다. ID: " + restaurantId));

        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        double averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        restaurant.updateAvgRating(Math.round(averageRating * 100.0) / 100.0);
    }
}
