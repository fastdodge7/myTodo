package com.toyproject.mytodo.Controller;

import com.toyproject.mytodo.Dto.LoginFormDto;
import com.toyproject.mytodo.Entity.User;
import com.toyproject.mytodo.Exceptions.LoginUserNotFoundException;
import com.toyproject.mytodo.Service.Auth.AuthenticationService;
import com.toyproject.mytodo.Service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(value = {"loginUserName", "loginUser"})
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AuthenticationService authenticationService;

    private UserService userService;

    @Autowired
    public LoginController(UserService userService){
        this.userService = userService;
    }


    @GetMapping (value = "login")
    public String login(){

        return "login";
    }

    @GetMapping (value = "/")
    public String loginWithSecurity(Model model){
        model.addAttribute("name", getLoggedInUserName());
        model.addAttribute("loginUser", getLoggedInUser());
        return "welcome";
    }

    private String getLoggedInUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    private User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<User> userList = userService.findByName(auth.getName());
        return userList.get(0);
    }


//    @PostMapping(value = "login")
//    public String login(LoginFormDto loginDto, Model model){
//
//        try{
//            User loginUser = authenticationService.authenticationWithNameAndEmail(loginDto.getName(), loginDto.getPassword());
//            model.addAttribute("loginUser", loginUser);
//            return "welcome";
//
//        }catch(LoginUserNotFoundException e){
//            model.addAttribute("errorMessage", "유저를 찾을 수 없음!!");
//            return "login";
//        }
//    }
}
