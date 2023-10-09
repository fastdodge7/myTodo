package com.toyproject.mytodo.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccessServiceWithoutLoginException extends IllegalStateException{
    public AccessServiceWithoutLoginException(String message){
        super(message);
    }
}
