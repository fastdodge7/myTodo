package com.toyproject.mytodo.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class TodoFormDto {
    @NotBlank
    private final String name;

    @NotBlank
    private final String task;

    @NotNull
    private final LocalDateTime dueDate;
}
