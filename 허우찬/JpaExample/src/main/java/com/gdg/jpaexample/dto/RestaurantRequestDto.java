package com.gdg.jpaexample.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequestDto {

    @NotBlank(message = "맛집 이름은 필수 입력 값입니다.")
    @Size(max = 50, message = "맛집 이름은 50자 이하로 입력해주세요.")
    private String name;

    @NotBlank(message = "맛집 주소는 필수 입력 값입니다.")
    private String address;

    private String phone;

    @NotBlank(message = "카테고리는 필수 입력 값입니다.")
    @Size(max = 30, message = "카테고리는 30자 이하로 입력해주세요.")
    private String category;
}
