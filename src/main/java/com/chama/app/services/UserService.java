package com.chama.app.services;

import com.chama.app.models.User;
import com.chama.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void addNewUser(User user) {
        userRepo.save(user);
    }

    public User getUserObject(int userId) {
        return userRepo.findByUserId(userId);
    }
}
