package com.chama.app.services;

import com.chama.app.models.Sequence;
import com.chama.app.repository.SequenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class SequenceService {

    @Autowired
    SequenceRepo sequenceRepo;

    public void createNewSequence(int id, String name, Sequence sequence) {

        Calendar calendar = Calendar.getInstance();

        Sequence holder = new Sequence();
        holder.setSequenceId(sequence.getSequenceId());
        holder.setUuid(sequence.getUuid());

        if (name.length() > 3){
            String abbreviation = name.substring(0, 3);
            holder.setPrefix(abbreviation.toUpperCase());
        }else {
            holder.setPrefix(name.toUpperCase());
        }

        holder.setNumber(sequence.getNumber());
        holder.setSuffix(calendar.get(Calendar.YEAR));
        holder.setChamaForeignKey(id);

        sequenceRepo.save(holder);
    }

    public String findChamaSequence(int chamaId) {
        Sequence sequence = sequenceRepo.findByChamaForeignKey(chamaId);
        return sequence.getPrefix();
    }
}
