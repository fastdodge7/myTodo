package com.toyproject.mytodo.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IllegalLoginUserException extends IllegalStateException{
    public IllegalLoginUserException(String message){
        super(message);
    }
}
