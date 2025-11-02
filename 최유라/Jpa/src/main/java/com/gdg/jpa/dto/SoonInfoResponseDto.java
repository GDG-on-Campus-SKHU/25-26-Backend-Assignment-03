package com.gdg.jpa.dto;

import com.gdg.jpa.domain.Soon;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SoonInfoResponseDto {
    private Long id;
    private String first;
    private int limit;

    public static SoonInfoResponseDto from(Soon soon) {
        return SoonInfoResponseDto.builder()
                .id(soon.getId())
                .first(soon.getFirst())
                .limit(soon.getLimit())
                .build();
    }
}
