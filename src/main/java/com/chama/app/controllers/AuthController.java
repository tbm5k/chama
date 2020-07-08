package com.chama.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(){
        return "fragments/authentication/sign-in";
    }

    @GetMapping("/signup")
    public String signUpPage(){
        return "fragments/authentication/sign-up";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(){
        return "fragments/authentication/forgot-password";
    }

}
