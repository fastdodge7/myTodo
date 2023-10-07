package com.toyproject.mytodo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping(method={RequestMethod.GET}, value = "say-hello")
    @ResponseBody
    public String sayHello(){
        return "Hello! World!";
    }

    @RequestMapping(method={RequestMethod.GET}, value = "say-hello-html")
    public String sayHelloHTML(){
        return "say-hello";
    }
}
