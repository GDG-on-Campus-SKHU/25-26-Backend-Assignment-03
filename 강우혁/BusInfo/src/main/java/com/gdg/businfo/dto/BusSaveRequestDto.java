package com.gdg.businfo.dto;

import lombok.Getter;

@Getter
public class BusSaveRequestDto {
    private String busNumber;
    private Long routeId;
    private Long companyId;
    private String type;
}
