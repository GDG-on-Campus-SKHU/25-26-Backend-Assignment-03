package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Restaurant;
import com.gdg.jpaexample.domain.Review;
import com.gdg.jpaexample.domain.User;
import com.gdg.jpaexample.dto.ReviewRequestDto;
import com.gdg.jpaexample.dto.ReviewResponseDto;
import com.gdg.jpaexample.repository.RestaurantRepository;
import com.gdg.jpaexample.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    @Transactional
    public ReviewResponseDto createReview(Long userId, ReviewRequestDto requestDto) {
        User user = userService.findUserById(userId);
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
                .orElseThrow(() -> new NoSuchElementException("맛집을 찾을 수 없습니다. ID: " + requestDto.getRestaurantId()));

        Optional<Review> existingReview = reviewRepository.findByUserIdAndRestaurantId(userId, requestDto.getRestaurantId());
        if (existingReview.isPresent()) {
            throw new IllegalStateException("이미 해당 맛집에 리뷰를 작성하셨습니다.");
        }

        Review review = Review.builder()
                .rating(requestDto.getRating())
                .comment(requestDto.getComment())
                .user(user)
                .restaurant(restaurant)
                .build();
        Review savedReview = reviewRepository.save(review);

        restaurantService.updateRestaurantAverageRating(restaurant.getId());

        return ReviewResponseDto.fromEntity(savedReview);
    }

    @Transactional(readOnly = true)
    public ReviewResponseDto getReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("리뷰를 찾을 수 없습니다. ID: " + id));
        return ReviewResponseDto.fromEntity(review);
    }

    @Transactional
    public ReviewResponseDto updateReview(Long reviewId, Long userId, ReviewRequestDto requestDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("리뷰를 찾을 수 없습니다. ID: " + reviewId));

        if (!review.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("리뷰 작성자만 수정할 수 있습니다.");
        }
        if (!review.getRestaurant().getId().equals(requestDto.getRestaurantId())) {
            throw new IllegalArgumentException("리뷰의 맛집 정보는 변경할 수 없습니다.");
        }

        review.update(requestDto.getRating(), requestDto.getComment());
        restaurantService.updateRestaurantAverageRating(review.getRestaurant().getId());

        return ReviewResponseDto.fromEntity(review);
    }

    @Transactional
    public void deleteReview(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("리뷰를 찾을 수 없습니다. ID: " + reviewId));

        if (!review.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("리뷰 작성자만 삭제할 수 있습니다.");
        }

        Long restaurantId = review.getRestaurant().getId();
        reviewRepository.delete(review);
        restaurantService.updateRestaurantAverageRating(restaurantId);
    }
}
