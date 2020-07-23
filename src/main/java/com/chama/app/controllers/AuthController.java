package com.chama.app.controllers;

import com.chama.app.repository.UserRepo;
import com.chama.app.services.MailService;
import com.chama.app.services.OtpService;
import com.chama.app.services.UserService;
import com.chama.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder passwordEncoder;

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

        //getting the username and email from the submitted form by the client
        username = user.getUsername();
        email = user.getEmail();

        if(userRepo.findByUsername(user.getUsername()) != null){
            model.addAttribute("usernameError", "Username is taken");
            return "fragments/authentication/sign-up";
        }

        if(userRepo.findByEmail(user.getEmail()) != null){
            model.addAttribute("emailError","Email exists");
            return "fragments/authentication/sign-up";
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            model.addAttribute("passwordError","Passwords don't match");
            return "fragments/authentication/sign-up";
        }else{

            //Defining user parameters
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setUuid(user.getUuid());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            //calling the add method to save the validated user
            userService.addNewUser(newUser);
            //generate otp for the new user
            otpGeneration(username, email);
            return "fragments/authentication/otp";
        }
    }

    //method is called when the opt client submits the opt in the otp form
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

    //Generate otp per as per the username
    public void otpGeneration(String username, String email){

        int opt = otpService.otpGeneration(username);

        mailService.sendMail(email, String.valueOf(opt));

    }

    @GetMapping("/resetPassword")
    public String resetPassword(){
        return "fragments/authentication/forgot-password";
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
