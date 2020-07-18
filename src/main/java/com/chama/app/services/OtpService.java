package com.chama.app.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    //method to randomly generate a opt
    public int otpGeneration(String key) {

        Random random = new Random();
         int otp = 100000 + random.nextInt(900000);

        return otp;
    }
}
