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
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CauseController {

	private static final String VIEWS_CAUSE_CREATE_OR_UPDATE_FORM = "causes/createOrUpdateCauseForm";
    private final ClinicService clinicService;


    @Autowired
    public CauseController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @RequestMapping(value = { "/causes"})
    public String showCauseList(Map<String, Object> model) {
        List<Cause> causes = new ArrayList<>();
        causes.addAll(this.clinicService.findCauses());
        model.put("causes", causes);
        return "causes/causeList";
    }

    @RequestMapping(value = "/causes/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Cause cause = new Cause();
        cause.setIsClosed(false);
        model.put("cause", cause);
        return VIEWS_CAUSE_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/causes/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Cause cause, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_CAUSE_CREATE_OR_UPDATE_FORM;
        } else {
            this.clinicService.saveCause(cause);
            return "redirect:/causes";    
        }
    }
    
    @RequestMapping("/causes/{causeId}")
    public ModelAndView showCause(@PathVariable("causeId") int causeId) {
        ModelAndView mav = new ModelAndView("causes/causeDetails");
        mav.addObject("cause",this.clinicService.findCauseById(causeId));
        return mav;
    }

}

