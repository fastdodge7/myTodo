package com.toyproject.mytodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class MyTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTodoApplication.class, args);
    }

}
