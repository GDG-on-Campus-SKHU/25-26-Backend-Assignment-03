package com.gdg.jpaexample.dto.itemdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ItemSaveRequestDto {
    private Long dayId;
    private String title;
    @NotNull(message = "price는 필수입니다.")
    @Min(value = 1, message = "1보다 큰 값을 적어주세요.")
    private Integer price;
    @NotNull(message = "amount는 필수입니다.")
    @Min(value = 1, message = "1보다 큰 값을 적어주세요.")
    private Integer amount;
}
