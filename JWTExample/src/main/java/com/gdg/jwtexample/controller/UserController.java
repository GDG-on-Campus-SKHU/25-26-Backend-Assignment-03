package com.gdg.jwtexample.controller;

import com.gdg.jwtexample.dto.TokenDto;
import com.gdg.jwtexample.dto.UserInfoResponseDto;
import com.gdg.jwtexample.dto.UserSignUpDto;
import com.gdg.jwtexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<TokenDto> signUp(@RequestBody UserSignUpDto userSignUpDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(userSignUpDto));
    }

    @GetMapping
    public ResponseEntity<UserInfoResponseDto> getUserInfo(Principal principal) {
        return ResponseEntity.ok(userService.findUserByPrincipal(principal));
    }
}