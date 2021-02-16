package com.chama.app.controllers;

import com.chama.app.models.MemberContribution;
import com.chama.app.models.Receipt;
import com.chama.app.models.UserIntegrations;
import com.chama.app.services.ContributionService;
import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContributionController {

    @Autowired
    ContributionService contributionService;
    @Autowired
    UserIntegrationsService userIntegrationsService;

    @PostMapping("/makeContribution")
    public String contribute(MemberContribution memberContribution){

        UserIntegrations member = userIntegrationsService.getMember(11).get();//get member obj from session

        memberContribution.setMember(member);
        contributionService.makeContribution(memberContribution);
        return "redirect:/userDashboard";
    }

    @GetMapping("/denyContribution/{id}")
    public String denyContribution(@PathVariable("id") int contributionId){
        contributionService.denyContribution(contributionId);
        return "redirect:/chamaDashboard";
    }

    @GetMapping("/acceptContribution")
    public String acceptContribution(MemberContribution contribution, Model model){

        MemberContribution memberContribution = contributionService.getMembersContribution(contribution.getMemberContributionId());

        Receipt receipt = new Receipt();
        receipt.setReceiptAmount(memberContribution.getAmount());
        receipt.setMember(memberContribution.getMember());

        model.addAttribute("members", memberContribution.getMember());
        model.addAttribute("receipt", receipt);
        //contributionService.updateContribution(memberContribution);
        return "fragments/receipt/receipt";
    }
}
