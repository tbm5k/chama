package com.chama.app.controllers;

import com.chama.app.models.Chama;
import com.chama.app.models.Invite;
import com.chama.app.models.MemberContribution;
import com.chama.app.models.Sequence;
import com.chama.app.repository.ChamaRepo;
import com.chama.app.services.*;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChamaController {

    @Autowired
    ChamaService chamaService;
    @Autowired
    ChamaRepo chamaRepo;
    @Autowired
    SequenceService sequenceService;
    @Autowired
    LoanService loanService;
    @Autowired
    ContributionService contributionService;
    @Autowired
    ReportService reportService;

    @GetMapping("/chama")
    public String createChamaTemplate(Model model){
        model.addAttribute("chama", new Chama());
        return "fragments/chama/chama";
    }

    @GetMapping("/chamaDashboard")
    public String chamaDashboard(Model model){
        int chamaId = 2;//chama id from the session
        model.addAttribute("invite", new Invite());
        model.addAttribute("loans", loanService.getLoanRequests(chamaId));

        List<MemberContribution> contributionList = contributionService.getMembersContributions(chamaId);
        List<MemberContribution> contributions = new ArrayList<>();

        for(MemberContribution contribution : contributionList){
            if(!contribution.isConfirm()){
                contributions.add(contribution);
            }
        }

        model.addAttribute("contributions", contributions);
        return "fragments/chama/chama-dashboard";
    }

    @PostMapping("/chama")
    public String createChama(Chama chama, Model model){

        if(chamaRepo.findByName(chama.getName()) != null){
            model.addAttribute("error", "Chama exists!");
            return "fragments/chama/chama";
        }else {
            chamaService.addNewChama(chama);

            //Getting the chama id to use in creating a sequence for the new chama
            int chamaId = chamaService.getChamaId(chama.getName());

            sequenceService.createNewSequence(chamaId, chama.getName(), new Sequence());//creating a new sequence on creation of a chama
            model.addAttribute("success", "Chama created!");
            return "redirect:chamaDashboard";
        }
    }

    @GetMapping("/report")
    public String generateReport() throws FileNotFoundException, JRException {
        reportService.exportReport(2);
        return "Report downloaded";
    }
}
