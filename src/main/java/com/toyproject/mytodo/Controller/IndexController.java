package com.toyproject.mytodo.Controller;

import com.toyproject.mytodo.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = {"loginUserName", "loginUser"})
public class IndexController {
//    @GetMapping("/")
//    public String index(@SessionAttribute(value = "loginUser", required = false) User loginUser) {
//        if (loginUser == null) {
//            return "redirect:/login";
//        }
//        return "redirect:/list-todo";
//    }
}
