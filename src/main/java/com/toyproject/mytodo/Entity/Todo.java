package com.toyproject.mytodo.Entity;


import com.toyproject.mytodo.Dto.TodoFormDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Todo {

    public void flipDoneFlag() { this.done = !this.done; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String task;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column(nullable = false)
    private Boolean done = false;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="owner_id")
    private User owner;

    @Builder
    public Todo(String name, String task, LocalDateTime createdDate, LocalDateTime dueDate, User owner){
        this.name = name;
        this.task = task;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.owner = owner;
    }

    public void updateTodo(TodoFormDto updateDto) {
        this.name = updateDto.getName();
        this.task = updateDto.getTask();
        this.dueDate = updateDto.getDueDate();
    }

    public void checkDone() {
        this.done = true;
    }
}
