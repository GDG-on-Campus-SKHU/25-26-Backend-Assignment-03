package com.gdg.businfo.dto;

import com.gdg.businfo.domain.Company;
import com.gdg.businfo.domain.Route;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RouteInfoResponseDto {
    private Long id;
    private String routeName;
    private String startPoint;
    private String endPoint;

    public static RouteInfoResponseDto from(Route route){
        return RouteInfoResponseDto.builder()
                .id(route.getId())
                .routeName(route.getRouteName())
                .startPoint(route.getStartPoint())
                .endPoint(route.getEndPoint())
                .build();
    }
}
