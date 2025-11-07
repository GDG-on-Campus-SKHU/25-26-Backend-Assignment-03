package com.gdg.jwtexample.dto;

import lombok.Getter;

@Getter
public class UserSignUpDto {
    private String email;
    private String password;
    private String name;
}
