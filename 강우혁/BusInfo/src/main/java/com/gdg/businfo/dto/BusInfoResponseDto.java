package com.gdg.businfo.dto;

import com.gdg.businfo.domain.Bus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BusInfoResponseDto {
    private Long id;
    private String busNumber;
    private Long companyId;
    private Long routeId;
    private String type;

    public static BusInfoResponseDto from(Bus bus){
        return BusInfoResponseDto.builder()
                .id(bus.getId())
                .busNumber(bus.getBusNumber())
                .companyId(bus.getCompany().getId())
                .routeId(bus.getRoute().getId())
                .type(bus.getType())
                .build();
    }
}
