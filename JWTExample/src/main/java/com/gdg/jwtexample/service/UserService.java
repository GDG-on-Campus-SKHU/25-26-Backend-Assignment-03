package com.gdg.jwtexample.service;

import com.gdg.jwtexample.domain.Role;
import com.gdg.jwtexample.domain.User;
import com.gdg.jwtexample.dto.TokenDto;
import com.gdg.jwtexample.dto.UserInfoResponseDto;
import com.gdg.jwtexample.dto.UserSignUpDto;
import com.gdg.jwtexample.jwt.TokenProvider;
import com.gdg.jwtexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public TokenDto signUp(UserSignUpDto userSignUpDto) {
        User user = userRepository.save(User.builder()
                .email(userSignUpDto.getEmail())
                .password(passwordEncoder.encode(userSignUpDto.getPassword()))
                .name(userSignUpDto.getName())
                .role(Role.ROLE_USER)
                .build());

        String accessToken = tokenProvider.createAccessToken(user);

        return TokenDto.builder()
                .accessToken(accessToken)
                .build();
    }

    public UserInfoResponseDto findUserByPrincipal(Principal principal) {
        Long userId = Long.parseLong(principal.getName());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        return UserInfoResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().name())
                .build();
    }
}