package com.example.jpaexample.controller;

import com.example.jpaexample.dto.CompanyInfoResponseDto;
import com.example.jpaexample.dto.CompanySaveRequestDto;
import com.example.jpaexample.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyInfoResponseDto> saveCompany(
            @RequestBody CompanySaveRequestDto companySaveRequestDto) {
        CompanyInfoResponseDto savedCompany = companyService.saveCompany(companySaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompanyById(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyInfoResponseDto> getCompany(@PathVariable Long companyId) {
        CompanyInfoResponseDto companyInfo = companyService.getCompany(companyId);
        return ResponseEntity.ok(companyInfo);
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
}
