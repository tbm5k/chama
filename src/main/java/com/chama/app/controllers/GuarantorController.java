package com.chama.app.controllers;

import com.chama.app.models.Guarantor;
import com.chama.app.models.UserIntegrations;
import com.chama.app.services.GuarantorService;
import com.chama.app.services.UserIntegrationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuarantorController {

    @Autowired
    GuarantorService guarantorService;
    @Autowired
    UserIntegrationsService userIntegrationsService;

    /*
    *Yet to implement guarantor requesting
    *
    @PostMapping("/requestGuarantor")
    public String requestGuarantor(Guarantor guarantor){
        UserIntegrations ui = userIntegrationsService.getMember(guarantor.getGuarantor().getUserIntegrationsId()).get();
        guarantor.setGuarantor(ui);
        //guarantorService.requestGuarantor(guarantor);
        return "fragments/Loan/request-loan";
    }
     */

}
