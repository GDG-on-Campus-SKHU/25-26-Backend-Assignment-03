package com.example.jpaexample.service;

import com.example.jpaexample.domain.Company;
import com.example.jpaexample.dto.CompanyInfoResponseDto;
import com.example.jpaexample.dto.CompanySaveRequestDto;
import com.example.jpaexample.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyInfoResponseDto saveCompany(CompanySaveRequestDto companySaveRequestDto) {
        Company company = Company.builder()
                .name(companySaveRequestDto.getName())
                .foundingYear(companySaveRequestDto.getFoundingYear())
                .build();

        companyRepository.save(company);
        return CompanyInfoResponseDto.from(company);
    }

    @Transactional
    public CompanyInfoResponseDto getCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 회사를 찾을 수 없습니다."));
        return CompanyInfoResponseDto.from(company);
    }

    @Transactional
    public List<CompanyInfoResponseDto> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(CompanyInfoResponseDto::from)
                .toList();
    }

    @Transactional
    public void deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
    }
}
