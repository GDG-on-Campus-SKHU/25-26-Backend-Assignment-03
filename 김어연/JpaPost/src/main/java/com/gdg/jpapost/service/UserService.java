package com.gdg.jpapost.service;

import com.gdg.jpapost.domain.User;
import com.gdg.jpapost.dto.UserInfoResponseDto;
import com.gdg.jpapost.dto.UserSaveRequestDto;
import com.gdg.jpapost.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserInfoResponseDto saveUser(UserSaveRequestDto userSaveRequestDto) {
        User user = User.builder()
                .name(userSaveRequestDto.getName())
                .email(userSaveRequestDto.getEmail())
                .build();

        userRepository.save(user);

        return UserInfoResponseDto.from(user);
    }

    @Transactional
    public void delectUser(Long user) {
        userRepository.deleteById(user);
    }
}
