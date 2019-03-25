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

import org.springframework.dao.DataAccessException;
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

    Collection<PetType> findPetTypes() throws DataAccessException;

    Owner findOwnerById(int id) throws DataAccessException;
    
    Vet findVetById(int id) throws DataAccessException;

    Pet findPetById(int id) throws DataAccessException;

    void savePet(Pet pet) throws DataAccessException;

    void saveVisit(Visit visit) throws DataAccessException;
    
	void saveVet(Vet vet) throws DataAccessException;
	
    void saveOwner(Owner owner) throws DataAccessException;

    Collection<Vet> findVets() throws DataAccessException;

    Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException;

	Collection<Visit> findVisitsByPetId(int petId);

	void deletePet(Pet pet) throws DataAccessException;

    void saveHotel(Hotel hotel) throws DataAccessException;
    
    Collection<Hotel> findHotelsByPetId(int petId);

    void deleteHotel(int hotelId) throws DataAccessException;

	Hotel findHotelById(int hotelId) throws DataAccessException;
	
	void deleteAllHotelsByPetId(int petId) throws DataAccessException;

	void deleteVisit(int visitId) throws DataAccessException;

	Visit findVisitsById(int visitId) throws DataAccessException;
	
	void deleteVet(Vet vet) throws DataAccessException;
	
	void deleteAllVisitsByPetId(int petId) throws DataAccessException;

	void deleteOwner(Owner owner) throws DataAccessException;
	
	Collection<Specialty> findVetSpecialities() throws DataAccessException;
	
	void saveCause(Cause cause) throws DataAccessException;
	
	Cause findCauseById(int causeId) throws DataAccessException;
	
	Collection<Cause> findCauses() throws DataAccessException;
	
	Donation findByDonationId(int donationId) throws DataAccessException;
	
	void saveDonation(Donation donation) throws DataAccessException;
	
	Double totalBudget(int causeId) throws DataAccessException;
	
	Collection<Donation> findDonations(int causeId) throws DataAccessException;

}
