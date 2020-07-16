package com.chama.app.controllers;

import com.chama.app.repository.UserRepo;
import com.chama.app.services.UserService;
import com.chama.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/sign-in")
    public String loginPage(){
        return "fragments/authentication/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model){
        model.addAttribute("user", new User());
        return "fragments/authentication/sign-up";
    }

    @PostMapping("/sign-up")
    public String addNewUser(User user, Model model){

        User userExists = userRepo.findByEmail(user.getEmail());

        if(userRepo.findByEmail(user.getEmail()) != null){
            model.addAttribute("error","Email exists");
        } else{
            userService.addNewUser(user);
            model.addAttribute("success","Account created");
        }
        return "fragments/authentication/sign-up";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(){
        return "fragments/authentication/forgot-password";
    }

}
