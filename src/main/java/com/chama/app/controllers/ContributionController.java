package com.chama.app.controllers;

import com.chama.app.models.MemberContribution;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContributionController {

    @PostMapping("/makeContribution")
    public String contribute(MemberContribution memberContribution){
        System.out.println(memberContribution.toString());
        return "fragments/user/user-dashboard";
    }
}
