package com.toyproject.mytodo.Controller;


import com.toyproject.mytodo.Dto.TodoFormDto;
import com.toyproject.mytodo.Dto.UserFormDto;
import com.toyproject.mytodo.Entity.Todo;
import com.toyproject.mytodo.Entity.User;
import com.toyproject.mytodo.Service.TodoService;
import com.toyproject.mytodo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService, TodoService todoService){
        this.userService = userService;
    }

    @GetMapping("list-user")
    public String listAllUser(Model model){
        model.addAttribute("userList", userService.findAll());
        return "user/listUser";
    }

    @GetMapping("register-user")
    public String registerUser(){
        return "user/registerUser";
    }

    @PostMapping("register-user")
    public String registerUser(UserFormDto userDto){

        User newUser = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();

        userService.saveUser(newUser);
        return "user/registerUser";
    }
}
