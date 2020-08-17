package com.chama.app.controllers;

import com.chama.app.models.Receipt;
import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReceiptController {

    @Autowired
    UserIntegrationsService userIntegrationsService;

    @GetMapping("/receipt")
    public String getReceipt(Model model){
        //dynamically set the chama id
        //model.addAttribute("members", userIntegrationsService.findByChamaForeignKey(2));
        model.addAttribute("receipt", new Receipt());
        return "fragments/receipt/receipt";
    }

    @PostMapping("/receipt")
    public String setReceipt(){
        System.out.println("Receipt posted");
        return "fragments/receipt/receipt";
    }
    
}
