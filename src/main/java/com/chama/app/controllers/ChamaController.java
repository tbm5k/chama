package com.chama.app.controllers;

import com.chama.app.models.Chama;
import com.chama.app.models.Invite;
import com.chama.app.models.Sequence;
import com.chama.app.repository.ChamaRepo;
import com.chama.app.services.ChamaService;
import com.chama.app.services.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChamaController {

    @Autowired
    ChamaService chamaService;
    @Autowired
    ChamaRepo chamaRepo;
    @Autowired
    SequenceService sequenceService;

    @GetMapping("/chama")
    public String createChamaTemplate(Model model){
        model.addAttribute("chama", new Chama());
        return "fragments/chama/chama";
    }

    @GetMapping("/chamaDashboard")
    public String chamaDashboard(Model model){
        model.addAttribute("invite", new Invite());
        return "fragments/chama/chama-dashboard";
    }

    @PostMapping("/chama")
    public String createChama(Chama chama, Model model){

        if(chamaRepo.findByName(chama.getName()) != null){
            model.addAttribute("error", "Chama exists!");
            return "fragments/chama/chama";
        }else {
            chamaService.addNewChama(chama);
            Chama idFinder = chamaService.findByName(chama.getName());
            sequenceService.createNewSequence(idFinder.getId(), chama.getName(), new Sequence());
            model.addAttribute("success", "Chama created!");
            return "redirect:chamaDashboard";
        }
    }
}
