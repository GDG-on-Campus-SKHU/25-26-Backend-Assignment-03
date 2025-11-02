package com.example.jpaexample.service;

import com.example.jpaexample.domain.Company;
import com.example.jpaexample.domain.Snack;
import com.example.jpaexample.dto.SnackInfoResponseDto;
import com.example.jpaexample.dto.SnackSaveRequestDto;
import com.example.jpaexample.repository.CompanyRepository;
import com.example.jpaexample.repository.SnackRepository;
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
    public SnackInfoResponseDto saveSnack(SnackSaveRequestDto snackSaveRequestDto) {
        Company company = companyRepository.findById(snackSaveRequestDto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        Snack snack = Snack.builder()
                .s_name(snackSaveRequestDto.getSName())
                .company(company)
                .build();

        snackRepository.save(snack);

        return SnackInfoResponseDto.from(snack);
    }

    @Transactional
    public SnackInfoResponseDto getSnack(Long snackId) {
        Snack snack = snackRepository.findById(snackId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 간식 정보를 찾을 수 없습니다."));
        return SnackInfoResponseDto.from(snack);
    }

    @Transactional
    public List<SnackInfoResponseDto> getAllSnacks() {
        return snackRepository.findAll()
                .stream()
                .map(SnackInfoResponseDto::from)
                .toList();
    }

    @Transactional
    public SnackInfoResponseDto updateSnack(Long snackId, SnackSaveRequestDto snackSaveRequestDto) {
        Snack snack = snackRepository.findById(snackId)
                .orElseThrow(() -> new IllegalArgumentException("요청하신 간식 정보를 찾을 수 없습니다."));

        Company company = companyRepository.findById(snackSaveRequestDto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        snack.update(snackSaveRequestDto.getSName(), company);

        return SnackInfoResponseDto.from(snack);
    }

    @Transactional
    public void deleteSnack(Long snackId) {
        snackRepository.deleteById(snackId);
    }
}
