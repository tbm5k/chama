package com.chama.app.controllers;

import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReceiptController {

    @Autowired
    UserIntegrationsService userIntegrationsService;

    @GetMapping("/receipt")
    public String getReceipt(Model model){
        model.addAttribute("members", userIntegrationsService.findByChamaForeignKey(2));
        return "fragments/receipt/receipt";
    }
    
}
