package com.gdg.businfo.service;

import com.gdg.businfo.domain.Company;
import com.gdg.businfo.dto.CompanyInfoResponseDto;
import com.gdg.businfo.dto.CompanySaveRequestDto;
import com.gdg.businfo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    //create
    @Transactional
    public CompanyInfoResponseDto saveCompany(CompanySaveRequestDto companySaveRequestDto) {
        Company company = Company.builder()
                .companyName(companySaveRequestDto.getCompanyName())
                .phoneNumber(companySaveRequestDto.getPhoneNumber())
                .region(companySaveRequestDto.getRegion())
                .build();
        companyRepository.save(company);
        return CompanyInfoResponseDto.from(company);
    }
    //delete
    @Transactional
    public void deleteCompany(Long id) {
        companyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회사입니다.")
        );
        companyRepository.deleteById(id);
    }
}
