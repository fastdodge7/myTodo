package com.toyproject.mytodo.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginUserNotFoundException extends IllegalStateException{
    public LoginUserNotFoundException(String message){
        super(message);
    }
}
