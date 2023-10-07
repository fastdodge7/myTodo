package com.toyproject.mytodo.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@RequiredArgsConstructor
public class TodoFormDto {
    private final String name;
    private final String task;

    private final LocalDateTime dueDate;

    private final Long ownerId;
}
