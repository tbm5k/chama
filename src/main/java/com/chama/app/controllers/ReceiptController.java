package com.chama.app.controllers;

import com.chama.app.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReceiptController {

    @Autowired
    MemberService memberService;

    @GetMapping("/receipt")
    public String getReceipt(Model model){
        model.addAttribute("members", memberService.findAllMembers());
        return "fragments/receipt/receipt";
    }
    
}
