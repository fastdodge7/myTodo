package com.toyproject.mytodo.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserFormDto {
    @NotBlank
    private final String name;

    @Email
    private final String email;
}
