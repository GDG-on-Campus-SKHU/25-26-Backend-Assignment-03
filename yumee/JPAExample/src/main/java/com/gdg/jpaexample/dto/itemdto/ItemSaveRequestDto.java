package com.gdg.jpaexample.dto.itemdto;

import lombok.Getter;

@Getter

public class ItemSaveRequestDto {
    private Long dayId;
    private String title;
    private int price;
    private int amount;
}
