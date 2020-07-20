package com.chama.app.services;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    LoadingCache<String, Integer> otpCache;

    public OtpService(){
        super();
        otpCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(String s) throws Exception {
                return 0;
            }
        });
    }

    //This method is used to push the opt number against Key. Rewrite the OTP if it exists
    //Using user id  as key
    public int otpGeneration(String key) {

        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return otp;
    }

    //returns the otp against key(first name)
    public int getOtp(String key) {

        try {
            otpCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void clearOtp(String key) {
        otpCache.invalidate(key);
    }
}
