package com.gdg.businfo.controller;

import com.gdg.businfo.dto.CompanyInfoResponseDto;
import com.gdg.businfo.dto.CompanySaveRequestDto;
import com.gdg.businfo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyInfoResponseDto> saveCompany(@RequestBody CompanySaveRequestDto companySaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.saveCompany(companySaveRequestDto));
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<CompanyInfoResponseDto> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
