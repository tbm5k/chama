package com.chama.app.controllers;

import com.chama.app.models.Chama;
import com.chama.app.repository.ChamaRepo;
import com.chama.app.services.ChamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChamaController {

    @Autowired
    ChamaService chamaService;

    @Autowired
    ChamaRepo chamaRepo;

    @GetMapping("/chama")
    public String createChamaTemplate(Model model){
        model.addAttribute("chama", new Chama());
        return "fragments/chama/chama";
    }

    @PostMapping("/chama")
    public String createChama(Chama chama, Model model){

        if(chamaRepo.findByName(chama.getName()) != null){
            model.addAttribute("error", "Chama exists!");
        }else {
            chamaService.addNewChama(chama);
            model.addAttribute("success", "Chama created!");
        }
        return "fragments/chama/chama";
    }
}
