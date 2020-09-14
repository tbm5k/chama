package com.chama.app.controllers;

import com.chama.app.models.Allocation;
import com.chama.app.services.AllocationService;
import com.chama.app.services.ReceiptService;
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
    @Autowired
    ReceiptService receiptService;

    @GetMapping("/allocation")
    public String allocationPage(Model model){
        model.addAttribute("allocation", new Allocation());
        model.addAttribute("members", userIntegrationsService.getChamaMembers(1));
        return "fragments/allocation/allocation.html";
    }

    @PostMapping("/allocation")
    public String createAllocation(Allocation allocation){

        Allocation allocationHolder = new Allocation();
        allocationHolder.setAllocationId(allocation.getAllocationId());
        allocationHolder.setUuid(allocation.getUuid());
        allocationHolder.setAmount(allocation.getAmount());
        allocationHolder.setReceiptDate(allocation.getReceiptDate());
        allocationHolder.setReceiptNumber(receiptService.getLastReceipt().getReceiptNumber());
        allocationHolder.setAllocationPeriod(allocation.getAllocationPeriod());
        allocationHolder.setMemberId(allocation.getMemberId());

        allocationService.addAllocation(allocationHolder);
        
        return "redirect:chamaDashboard";
    }
}