package com.chama.app.services;

import com.chama.app.models.Chama;
import com.chama.app.repository.ChamaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamaService {

    @Autowired
    ChamaRepo chamaRepo;

    public void addNewChama(Chama chama) {
        chamaRepo.save(chama);
    }

    public int getChamaId(String name) {
        Chama chama = chamaRepo.findByName(name);
        return chama.getChamaId();
    }

    public Chama getChamaObject(int chamaId){
        return chamaRepo.findByChamaId(chamaId);
    }
}
