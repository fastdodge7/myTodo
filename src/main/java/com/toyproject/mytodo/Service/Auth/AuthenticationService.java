package com.toyproject.mytodo.Service.Auth;

import com.toyproject.mytodo.Entity.User;
import com.toyproject.mytodo.Exceptions.DuplicatedUserException;
import com.toyproject.mytodo.Exceptions.LoginUserNotFoundException;
import com.toyproject.mytodo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private UserService userService;

    @Autowired
    public AuthenticationService(UserService userService){
        this.userService = userService;
    }

    public User authenticationWithNameAndEmail(String name, String email){

        List<User> users = userService.findByEmail(email).stream()
                .filter((element) -> element.getName().equals(name))
                .toList();

        if(users.size() > 1) throw new DuplicatedUserException("이메일, 이름 정보가 중복되는 이용자가 있습니다.");
        if(users.isEmpty()) throw new LoginUserNotFoundException("이메일, 이름 정보와 일치하는 유저가 없습니다.");
        return users.get(0);
    }

//    public Boolean authentication(String name, String password){
//        return name.equals("asd") && password.equals("asd");
//    }

}
