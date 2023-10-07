package com.toyproject.mytodo.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserFormDto {
    private final String name;
    private final String email;
}
