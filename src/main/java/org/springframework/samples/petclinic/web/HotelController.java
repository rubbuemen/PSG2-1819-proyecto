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

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Hotel;
import org.springframework.samples.petclinic.model.Pet;
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
public class HotelController {

    private final ClinicService clinicService;


    @Autowired
    public HotelController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId
     * @return Pet
     */
    @ModelAttribute("hotel")
    public Hotel loadPetWithHotel(@PathVariable("petId") int petId) {
        Pet pet = this.clinicService.findPetById(petId);
        Hotel hotel = new Hotel();
        pet.addHotel(hotel);
        return hotel;
    }

    @RequestMapping(value = "/owners/*/pets/{petId}/hotels/new", method = RequestMethod.GET)
    public String initNewHotelForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        return "pets/createOrUpdateHotelForm";
    }

    @RequestMapping(value = "/owners/{ownerId}/pets/{petId}/hotels/new", method = RequestMethod.POST)
    public String processNewHotelForm(@Valid Hotel hotel, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateHotelForm";
        } else {
            this.clinicService.saveHotel(hotel);
            return "redirect:/owners/{ownerId}";
        }
    }

    @RequestMapping(value = "/owners/*/pets/{petId}/hotels", method = RequestMethod.GET)
    public String showHotels(@PathVariable int petId, Map<String, Object> model) {
        model.put("hotels", this.clinicService.findPetById(petId).getHotels());
        return "hotelList";
    }
    
    @RequestMapping(value = "/owners/{ownerId}/pets/{petId}/hotels/{hotelId}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("hotelId") int hotelId, @PathVariable("petId") int petId) {
       
        Pet pet=this.clinicService.findPetById(petId);
        Hotel hotel=this.clinicService.findHotelById(hotelId);
        pet.deleteHotel(hotel);
        this.clinicService.savePet(pet);

        this.clinicService.deleteHotel(hotelId);
        return "redirect:/owners/{ownerId}";
     
    }

}
