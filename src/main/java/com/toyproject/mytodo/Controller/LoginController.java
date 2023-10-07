package com.toyproject.mytodo.Controller;

import com.toyproject.mytodo.Dto.LoginFormDto;
import com.toyproject.mytodo.Service.Auth.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AuthenticationService authenticationService;

    @Autowired
    public LoginController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }


    @GetMapping (value = "login")
    public String login(){

        return "login";
    }

    @PostMapping(value = "login")
    public String login(LoginFormDto loginDto, Model model){
        if(authenticationService.authentication(loginDto.getName(), loginDto.getPassword())){
            model.addAttribute("userName", loginDto.getName());
            return "welcome";
        }
        model.addAttribute("errorMessage", "Invalid auth data!");
        return "login";
    }
}
