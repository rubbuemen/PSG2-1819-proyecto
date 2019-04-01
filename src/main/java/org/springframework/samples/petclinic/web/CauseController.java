/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CauseController {

	private static final String VIEWS_CAUSE_CREATE_OR_UPDATE_FORM = "causes/createOrUpdateCauseForm";
    private final ClinicService clinicService;


    @Autowired
    public CauseController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = { "/causes"} )
    public String showCauseList(Map<String, Object> model) {
        List<Cause> causes = new ArrayList<>();
        causes.addAll(this.clinicService.findCauses());
     
        List<Double> donations=new ArrayList<>(this.clinicService.findDonationsByCauses(causes));
        
        Map<Cause,Double> res=new HashMap<Cause,Double>();
        for(int i=0;i<causes.size();i++) {
        	res.put(causes.get(i), donations.get(i));
        }
        model.put("map", res);
        return "causes/causeList";
    }

    @GetMapping(value = "/causes/new")
    public String initCreationForm(Map<String, Object> model) {
        Cause cause = new Cause();
        cause.setIsClosed(false);
        model.put("cause", cause);
        return VIEWS_CAUSE_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/causes/new")
    public String processCreationForm(@Valid Cause cause, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CAUSE_CREATE_OR_UPDATE_FORM;
        } else {
            this.clinicService.saveCause(cause);
            return "redirect:/causes";    
        }
    }
    
    @GetMapping("/causes/{causeId}")
    public ModelAndView showCause(@PathVariable("causeId") int causeId, Map<String, Object> model) {
    	Collection<Donation> donations = new ArrayList<>();
    	donations = this.clinicService.findDonations(causeId);
        ModelAndView mav = new ModelAndView("causes/causeDetails");
        mav.addObject(this.clinicService.findCauseById(causeId));
        model.put("donations", donations);
        mav.addObject("cause",this.clinicService.findCauseById(causeId));
        return mav;
    }

}

