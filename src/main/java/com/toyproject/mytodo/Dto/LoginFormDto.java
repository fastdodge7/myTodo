package com.toyproject.mytodo.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginFormDto {
    private final String name;
    private final String password;
}
