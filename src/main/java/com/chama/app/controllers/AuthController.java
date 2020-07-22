package com.chama.app.controllers;

import com.chama.app.repository.UserRepo;
import com.chama.app.services.MailService;
import com.chama.app.services.OtpService;
import com.chama.app.services.UserService;
import com.chama.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    OtpService otpService;

    @Autowired
    MailService mailService;

    //Global variables
    public String username;
    public String email;

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

        username = user.getUsername();
        email = user.getEmail();

        if(userRepo.findByUsername(user.getUsername()) != null){
            model.addAttribute("usernameError", "Username is taken");
            return "fragments/authentication/sign-up";
        }

        if(userRepo.findByEmail(user.getEmail()) != null){
            model.addAttribute("emailError","Email exists");
            return "fragments/authentication/sign-up";
        } else{
            userService.addNewUser(user);
            otpGeneration(username, email);
            return "fragments/authentication/otp";
        }
    }

    @GetMapping("/otpValidation")
    public String otpValidation(@RequestParam("otp") Integer otp, Model model){

        if(otp >= 0){
            int serverOtp = otpService.getOtp(username);

            if(otp > 0){
                if(otp == serverOtp){
                    otpService.clearOtp(username);
                    model.addAttribute("valid","Valid");
                    return "redirect:chama";
                }else {
                    model.addAttribute("error","Invalid token, try again");
                }
            }
        }

        return "fragments/authentication/otp";
    }

    public void otpGeneration(String username, String email){

        int opt = otpService.otpGeneration(username);

        mailService.sendMail(email, String.valueOf(opt));

    }

    @GetMapping("/resetPassword")
    public String resetPassword(){
        return "fragments/authentication/forgot-password";
    }

}
