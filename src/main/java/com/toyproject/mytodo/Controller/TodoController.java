package com.toyproject.mytodo.Controller;


import com.toyproject.mytodo.Dto.TodoFormDto;
import com.toyproject.mytodo.Entity.Todo;
import com.toyproject.mytodo.Entity.User;
import com.toyproject.mytodo.Exceptions.DuplicatedUserException;
import com.toyproject.mytodo.Exceptions.IllegalLoginUserException;
import com.toyproject.mytodo.Exceptions.AccessServiceWithoutLoginException;
import com.toyproject.mytodo.Exceptions.LoginUserNotFoundException;
import com.toyproject.mytodo.Service.TodoService;
import com.toyproject.mytodo.Service.UserService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@SessionAttributes(value = "loginUserName")
public class TodoController {
    private UserService userService;
    private TodoService todoService;

    @Autowired
    public TodoController(UserService userService, TodoService todoService){
        this.userService = userService;
        this.todoService = todoService;
    }

    @GetMapping("list-todo")
    public String listAllTodo(@SessionAttribute(value = "loginUser", required = false) User loginUser, Model model){
        if(loginUser == null) throw new LoginUserNotFoundException("현재 로그인되지 않은 상태입니다.");

        model.addAttribute("todoList", todoService.findByOwner(loginUser));
        return "todoList/todoList";
    }

    @GetMapping("register-todo")
    public String registerTodo(Model model){
        return "todoList/registerTodo";
    }

    @PostMapping("register-todo")
    public String registerTodo(@Valid TodoFormDto todoFormDto,
                               BindingResult result,
                               @SessionAttribute(value = "loginUser", required = false) User loginUser,
                               Model model){

        if(loginUser == null) throw new LoginUserNotFoundException("현재 로그인되지 않은 상태입니다.");
        if(result.hasErrors()){
            return "todoList/failAlarmPage/registerTodoFail";
        }

        todoService.addTodo(todoFormDto, loginUser);
        return "redirect:/list-todo";
    }

    @GetMapping("delete-todo")
    public String deleteTodo(Model model,
                             @RequestParam Long id,
                             @SessionAttribute(value = "loginUser") User loginUser) {

        try{
            todoService.deleteTodoById(id, loginUser);
        }catch(IllegalStateException e){
            return "todoList/failAlarmPage/deleteTodoFail";
        }
        return "redirect:/list-todo";
    }

    @GetMapping("update-todo")
    public String updateTodo(Model model,
                             @RequestParam Long id,
                             @SessionAttribute(value = "loginUser", required = false) User loginUser) {
        try{
            Todo target = todoService.findById(id).orElseThrow(() -> new IllegalStateException());
            if (loginUser == null) throw new LoginUserNotFoundException();
            if (target.getOwner().getId() != loginUser.getId()) throw new IllegalLoginUserException();

            model.addAttribute("beforeTodo", target);
        }catch(IllegalStateException e){
            return "todoList/failAlarmPage/deleteTodoFail";
        }
        return "todoList/updateTodo";
    }

    @PostMapping("update-todo")
    public String updateTodo(Model model,
                             @RequestParam Long id,
                             @SessionAttribute(value = "loginUser", required = false) User loginUser,
                             TodoFormDto updateDto) {

        try{
            todoService.updateTodoById(id, loginUser, updateDto);
        }catch(IllegalStateException e){
            return "todoList/failAlarmPage/deleteTodoFail";
        }
        return "redirect:/list-todo";
    }

    private User getLoginUser(String loginUserName) throws RuntimeException{
        if(loginUserName == null) throw new AccessServiceWithoutLoginException("현재 로그인한 유저가 없습니다.");
        List<User> users = userService.findByName(loginUserName);

        if(users.isEmpty()) throw new IllegalLoginUserException("회원 등록된 유저가 아닙니다");
        if(users.size() > 1) throw new DuplicatedUserException("유저가 중복되었습니다");
        return users.get(0);
    }
}
