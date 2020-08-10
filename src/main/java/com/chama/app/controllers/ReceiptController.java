package com.chama.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReceiptController {

    @GetMapping("/receipt")
    public String getReceipt(){
        return "fragments/receipt/receipt";
    }
    
}
