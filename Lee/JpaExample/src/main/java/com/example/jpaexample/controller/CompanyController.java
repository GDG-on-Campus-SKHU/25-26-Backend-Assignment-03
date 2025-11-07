package com.example.jpaexample.controller;

import com.example.jpaexample.dto.*;
import com.example.jpaexample.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyInfoResponseDto> saveCompany(@RequestBody CompanySaveRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.saveCompany(dto));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyInfoResponseDto> getCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyService.getCompany(companyId));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
        List<CompanyResponseDto> companyList = companyService.getAllCompanies();
        return ResponseEntity.ok(companyList);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }
}
