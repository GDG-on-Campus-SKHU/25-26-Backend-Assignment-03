package com.gdg.jpaexample.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequestDto {

    @NotNull(message = "맛집 ID는 필수 입력 값입니다.")
    private Long restaurantId;

    @NotNull(message = "별점은 필수 입력 값입니다.")
    @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
    @Max(value = 5, message = "별점은 5점 이하이어야 합니다.")
    private Integer rating;

    @NotBlank(message = "리뷰 내용은 필수 입력 값입니다.")
    @Size(min = 3, max = 1000, message = "리뷰 내용은 10자 이상 1000자 이하로 입력해주세요.")
    private String comment;
}
