package com.chama.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/userDashboard")
    public String getDashboard(){
        return "fragments/user/user-dashboard";
    }
}
