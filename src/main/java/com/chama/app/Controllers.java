package com.chama.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controllers {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

}
