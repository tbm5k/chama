package com.chama.app.controllers;

import com.chama.app.models.User;
import com.chama.app.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtpController {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    OtpService otpService;

    //An otp is genereated once this method is called and sent to the client via email
    @GetMapping("/generateOtp")
    public String optGeneration(User user){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        int otp = otpService.otpGeneration(username);

        mailMessage.setTo(user.getEmail());
        mailMessage.setText(String.valueOf(otp));

        mailSender.send(mailMessage);

        return "fragments/authentication/otp";
    }

}
