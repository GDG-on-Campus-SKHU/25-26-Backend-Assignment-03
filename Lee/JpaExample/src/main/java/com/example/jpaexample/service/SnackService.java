package com.example.jpaexample.service;

import com.example.jpaexample.domain.*;
import com.example.jpaexample.dto.*;
import com.example.jpaexample.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SnackService {

    private final SnackRepository snackRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public SnackInfoResponseDto saveSnack(SnackSaveRequestDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        Snack snack = Snack.builder()
                .name(dto.getName())
                .releaseYear(dto.getReleaseYear())
                .company(company)
                .build();

        snackRepository.save(snack);
        return SnackInfoResponseDto.from(snack);
    }

    @Transactional
    public SnackInfoResponseDto getSnack(Long snackId) {
        Snack snack = snackRepository.findById(snackId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과자입니다."));
        return SnackInfoResponseDto.from(snack);
    }

    @Transactional
    public SnackInfoResponseDto updateSnack(Long snackId, SnackSaveRequestDto dto) {
        Snack snack = snackRepository.findById(snackId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과자입니다."));

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        snack.update(dto.getName(), dto.getReleaseYear(), company);
        return SnackInfoResponseDto.from(snack);
    }

    @Transactional
    public void deleteSnack(Long snackId) {
        snackRepository.deleteById(snackId);
    }

    @Transactional
    public List<SnackInfoResponseDto> getAllSnacks() {
        return snackRepository.findAll().stream()
                .map(SnackInfoResponseDto::from)
                .toList();
    }
}
