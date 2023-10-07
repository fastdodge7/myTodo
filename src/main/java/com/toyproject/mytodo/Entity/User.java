package com.toyproject.mytodo.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name="TodoUser")
public class User {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    @OneToMany(targetEntity = Todo.class, mappedBy = "owner")
    private List<Todo> todoList;


    @Builder
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
}
