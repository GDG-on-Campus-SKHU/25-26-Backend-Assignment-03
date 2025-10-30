package com.gdg.jpaexample.dto.itemdto;

import com.gdg.jpaexample.domain.Item;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class ItemInfoResponseDto {
    private Long id;
    private String title;
    private int price;
    private int amount;

    private Long dayId;
    private int localDay;

    public static ItemInfoResponseDto from(Item item) {
        return ItemInfoResponseDto.builder()
                .id(item.getId())
                .title(item.getTitle())
                .price(item.getPrice())
                .amount(item.getAmount())
                .dayId(item.getDay().getId())
                .localDay(item.getDay().getDay())
                .build();
    }
}
