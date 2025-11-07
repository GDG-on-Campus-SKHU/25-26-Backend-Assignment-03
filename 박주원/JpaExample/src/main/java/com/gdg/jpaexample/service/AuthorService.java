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
    public AuthorInfoResponseDto saveAuthor(AuthorSaveRequestDto dto) {
        Author author = Author.create(dto.getName(), dto.getBirthYear());
        authorRepository.save(author);
        return AuthorInfoResponseDto.from(author);
    }

    @Transactional
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}
