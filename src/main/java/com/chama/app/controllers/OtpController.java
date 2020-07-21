package com.chama.app.controllers;

import com.chama.app.models.User;
import com.chama.app.services.MailService;
import com.chama.app.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OtpController {

    @Autowired
    OtpService otpService;

    @Autowired
    MailService mailService;

    //An otp is generated once this method is called and sent to the client via email
    @GetMapping("/generateOtp")
    public String otpGeneration(){

        String username = "ted";
        int opt = otpService.otpGeneration(username);

        mailService.sendMail("tedburg5@gmail.com", String.valueOf(opt));

        return "fragments/authentication/otp";
    }

    @GetMapping("/otpValidation")
    public String otpValidation(@RequestParam("otp") Integer otp, Model model){

        String username = "ted";

        if(otp >= 0){
            int serverOtp = otpService.getOtp(username);

            if(otp > 0){
                if(otp == serverOtp){
                    otpService.clearOtp(username);
                    model.addAttribute("valid","Valid");
                }else {
                    model.addAttribute("error","Invalid token, try again");
                }
            }
        }

        return "redirect:chama";
    }

}
