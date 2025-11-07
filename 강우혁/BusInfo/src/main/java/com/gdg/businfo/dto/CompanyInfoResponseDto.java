package com.gdg.businfo.dto;

import com.gdg.businfo.domain.Company;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyInfoResponseDto {
    private Long id;
    private String companyName;
    private String phoneNumber;
    private String region;

    public static CompanyInfoResponseDto from(Company company){
        return CompanyInfoResponseDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .phoneNumber(company.getPhoneNumber())
                .region(company.getRegion())
                .build();
    }
}
