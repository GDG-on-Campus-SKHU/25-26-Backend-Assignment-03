package com.gdg.jpaexample.service;

import com.gdg.jpaexample.domain.Author;
import com.gdg.jpaexample.dto.AuthorInfoResponseDto;
import com.gdg.jpaexample.dto.AuthorSaveRequestDto;
import com.gdg.jpaexample.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public AuthorInfoResponseDto save(AuthorSaveRequestDto dto) {
        Author author = Author.builder()
                .name(dto.getName())
                .nationality(dto.getNationality())
                .build();
        return AuthorInfoResponseDto.from(authorRepository.save(author));
    }

    @Transactional
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
