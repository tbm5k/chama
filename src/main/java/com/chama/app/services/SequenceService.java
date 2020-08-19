package com.chama.app.services;

import com.chama.app.models.Sequence;
import com.chama.app.repository.SequenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SequenceService {

    @Autowired
    SequenceRepo sequenceRepo;

    public void createNewSequence(String name, Sequence sequence) {

        Date date = new Date();

        Sequence holder = new Sequence();
        sequence.setId(sequence.getId());
        sequence.setUuid(sequence.getUuid());
        sequence.setPrefix(name);
        sequence.setNumber(sequence.getNumber());
        sequence.setSuffix(date.getYear());

        sequenceRepo.save(holder);
    }
}
