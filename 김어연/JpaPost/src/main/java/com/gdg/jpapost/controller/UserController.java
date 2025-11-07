package com.gdg.jpapost.controller;

import com.gdg.jpapost.dto.UserInfoResponseDto;
import com.gdg.jpapost.dto.UserSaveRequestDto;
import com.gdg.jpapost.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserInfoResponseDto> saveUser(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userSaveRequestDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserInfoResponseDto> deleteUserById(@PathVariable Long userId) {
        userService.delectUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
