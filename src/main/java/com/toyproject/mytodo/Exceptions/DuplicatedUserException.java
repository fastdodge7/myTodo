package com.toyproject.mytodo.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicatedUserException extends IllegalStateException{
    public DuplicatedUserException(String message){
        super(message);
    }
}
