package com.toyproject.mytodo.Controller;

import com.toyproject.mytodo.Dto.LoginFormDto;
import com.toyproject.mytodo.Entity.User;
import com.toyproject.mytodo.Exceptions.LoginUserNotFoundException;
import com.toyproject.mytodo.Service.Auth.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes(value = {"loginUserName", "loginUser"})
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

        try{
            User loginUser = authenticationService.authenticationWithNameAndEmail(loginDto.getName(), loginDto.getPassword());
            model.addAttribute("loginUser", loginUser);
            return "welcome";

        }catch(LoginUserNotFoundException e){
            model.addAttribute("errorMessage", "유저를 찾을 수 없음!!");
            return "login";
        }
    }
}
