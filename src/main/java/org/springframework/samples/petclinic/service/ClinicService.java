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
package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Hotel;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;


/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 */
public interface ClinicService {

    Collection<PetType> findPetTypes();

    Owner findOwnerById(int id);
    
    Vet findVetById(int id);

    Pet findPetById(int id);

    void savePet(Pet pet);

    void saveVisit(Visit visit);
    
	void saveVet(Vet vet);
	
    void saveOwner(Owner owner);

    Collection<Vet> findVets();

    Collection<Owner> findOwnerByLastName(String lastName);

	Collection<Visit> findVisitsByPetId(int petId);

	void deletePet(Pet pet) ;

    void saveHotel(Hotel hotel);
    
    Collection<Hotel> findHotelsByPetId(int petId);

    void deleteHotel(int hotelId);

	Hotel findHotelById(int hotelId);
	
	void deleteAllHotelsByPetId(int petId);
	
	
	

	void deleteVisit(int visitId);

	Visit findVisitsById(int visitId);
	
	void deleteVet(Vet vet);
	
	void deleteAllVisitsByPetId(int petId);

	void deleteOwner(Owner owner);
	
	Collection<Specialty> findVetSpecialities();
	
	void saveCause(Cause cause);
	
	Cause findCauseById(int causeId);
	
	Collection<Cause> findCauses();
	
	Donation findByDonationId(int donationId);
	
	void saveDonation(Donation donation);
	
	Double totalBudget(int causeId);

	List<Double> findDonationsByCauses(List<Cause> causes);

	Collection<Donation> findDonations(int causeId);

}
