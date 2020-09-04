package com.chama.app.controllers;

import com.chama.app.models.Allocation;
import com.chama.app.services.AllocationService;
import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AllocationController {

    @Autowired
    AllocationService allocationService;
    @Autowired
    UserIntegrationsService userIntegrationsService;

    @GetMapping("/allocation")
    public String allocationPage(Model model){
        model.addAttribute("allocation", new Allocation());
        model.addAttribute("members", userIntegrationsService.getChamaMembers(1));
        return "fragments/allocation/allocation.html";
    }

    @PostMapping("/allocation")
    public String createAllocation(Allocation allocation){
        allocationService.addAllocation(allocation);
        return "";
    }
}
