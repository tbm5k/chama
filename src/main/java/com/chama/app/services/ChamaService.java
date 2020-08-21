package com.chama.app.services;

import com.chama.app.models.Chama;
import com.chama.app.repository.ChamaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChamaService {

    @Autowired
    ChamaRepo chamaRepo;

    public void addNewChama(Chama chama) {
        chamaRepo.save(chama);
    }

    public Chama findByName(String name) {
        return chamaRepo.findByName(name);
    }
}
