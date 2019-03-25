package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/donations/new", method = RequestMethod.GET)
    public String initCreationForm(Cause cause, Map<String, Object> model) {
        Donation donation = new Donation();
        donation.setCause(cause);
        cause.addDonation(donation);
        model.put("donation", donation);
        return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/donations/new", method = RequestMethod.POST)
    public String processCreationForm(Cause cause, @Valid Donation donation, BindingResult result, Map<String, Object> model) {
    	donation.setCause(cause);
    	if (cause.getIsClosed()==true){
            result.rejectValue("client", "closed");
//        }else if((cause.getBudgetTarget())<1){
//        	result.rejectValue("amount", "overmuch");
        }
        if (result.hasErrors()) {
        	model.put("donation", donation);
            return VIEWS_DONATION_CREATE_OR_UPDATE_FORM;
        } else {
            cause.addDonation(donation);
            this.clinicService.saveDonation(donation);
            if(cause.getBudgetTarget() <= clinicService.totalBudget(cause.getId())){
            	cause.setIsClosed(true);
            this.clinicService.saveCause(cause);
            }
          
        return "redirect:/causes";
    }  
}

    }
