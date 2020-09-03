package com.chama.app.controllers;

import com.chama.app.models.Allocation;
import com.chama.app.services.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AllocationController {

    @Autowired
    AllocationService allocationService;

    @GetMapping("/allocation")
    public String allocationTemplate(Model model){
        model.addAttribute("allocation", new Allocation());
        return "";
    }

    @PostMapping("/allocation")
    public String createAllocation(Allocation allocation){

        return "";
    }
}
