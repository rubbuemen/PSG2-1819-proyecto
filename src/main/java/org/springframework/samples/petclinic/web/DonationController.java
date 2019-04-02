package org.springframework.samples.petclinic.web;


import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/causes/{causeId}")
public class DonationController {

	private static final String VIEWS_DONATION_CREATE_OR_UPDATE_FORM = "donations/createOrUpdateDonationForm";
    private final ClinicService clinicService;

    @Autowired
    public DonationController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }
    
    @ModelAttribute("cause")
    public Cause findOwner(@PathVariable("causeId") int causeId) {
        return this.clinicService.findCauseById(causeId);
    }
    
    @InitBinder("cause")
    public void initCauseBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = "/donations/new")
    public String initCreationForm(Cause cause, ModelMap model) {
        Donation donation = new Donation();
        cause.addDonation(donation);
    	donation.setDate(LocalDate.now());
        model.put("donation", donation);
        return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/donations/new")
    public String processCreationForm(@ModelAttribute Cause cause, @Valid Donation donation, BindingResult result, ModelMap model) {
    	donation.setCause(cause);
    	if (cause.getIsClosed()){
            result.rejectValue("client", "closed");
            result.rejectValue("amount", "closed");
    	} 
        if (result.hasErrors()) {
        	model.put("donation", donation);
            return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
        } else {
            this.clinicService.saveDonation(donation);
            cause.addDonation(donation);
            if(cause.getBudgetTarget() <= clinicService.totalBudget(donation.getCause().getId())){
            	cause.setIsClosed(true);
            this.clinicService.saveCause(cause);
            }
          
        return "redirect:/causes";
    }  
}

    }
