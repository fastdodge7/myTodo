package com.toyproject.mytodo.Controller;


import com.toyproject.mytodo.Dto.TodoFormDto;
import com.toyproject.mytodo.Entity.Todo;
import com.toyproject.mytodo.Entity.User;
import com.toyproject.mytodo.Service.TodoService;
import com.toyproject.mytodo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TodoController {
    private UserService userService;
    private TodoService todoService;

    @Autowired
    public TodoController(UserService userService, TodoService todoService){
        this.userService = userService;
        this.todoService = todoService;
    }

    @GetMapping("list-todo")
    public String listAllTodo(Model model){
        model.addAttribute("todoList", todoService.findAll());
        return "todoList/todoList";
    }

    @GetMapping("register-todo")
    public String registerTodo(Model model){
        return "todoList/registerTodo";
    }

    @PostMapping("register-todo")
    public String registerTodo(TodoFormDto todoFormDto){

        User owner = userService.findById(todoFormDto.getOwnerId()).orElseThrow(()
                -> new IllegalStateException("유효하지 않은 user id입니다."));

        Todo newTodo = Todo.builder()
                .task(todoFormDto.getTask())
                .createdDate(LocalDateTime.now())
                .dueDate(todoFormDto.getDueDate())
                .name(todoFormDto.getName())
                .owner(owner).build();

        todoService.saveTodo(newTodo);
        return "redirect:/list-todo";
    }
}
