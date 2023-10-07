package com.toyproject.mytodo.Service.Auth;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public Boolean authentication(String name, String password){
        return name.equals("asd") && password.equals("asd");
    }

}
