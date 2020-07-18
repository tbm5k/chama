package com.chama.app.controllers;

import com.chama.app.models.User;
import com.chama.app.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OtpController {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    OtpService otpService;

    //An otp is generated once this method is called and sent to the client via email
    @GetMapping("/generateOtp")
    public String otpGeneration(User user){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String firstName = user.getFirstName();
        int otp = otpService.otpGeneration(firstName);

        mailMessage.setTo(user.getEmail());
        mailMessage.setText(String.valueOf(otp));

        mailSender.send(mailMessage);

        return "fragments/authentication/otp";
    }

    @PostMapping("/otpValidation")
    public String otpValidation(int otp, Model model, User user){

        String firstName = user.getFirstName();

        if(otp >= 0){
            int serverOtp = otpService.getOtp(firstName);

            if(otp > 0){
                if(otp == serverOtp){
                    model.addAttribute("valid","Valid");
                }else {
                    model.addAttribute("error","Invalid token, try again");
                }
            }
        }

        return "redirect:fragments/chama/chama";
    }

}
