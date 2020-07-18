package com.chama.app.controllers;

import com.chama.app.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtpController {

    @Autowired
    OtpService otpService;

    //An otp is genereated once this method is called
    @GetMapping("/generateOtp")
    public String optGeneration(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        int otp = otpService.otpGeneration(username);

        return "fragments/authentication/otp";
    }

}
