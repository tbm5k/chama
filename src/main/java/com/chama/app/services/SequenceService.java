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
        holder.setId(sequence.getId());
        holder.setUuid(sequence.getUuid());

        if (name.length() > 3){
            String abbreviation = name.substring(0, 3);
            holder.setPrefix(abbreviation.toUpperCase());
        }else {
            holder.setPrefix(name.toUpperCase());
        }

        holder.setNumber(sequence.getNumber());
        holder.setSuffix(date.getYear());

        sequenceRepo.save(holder);
    }
}
