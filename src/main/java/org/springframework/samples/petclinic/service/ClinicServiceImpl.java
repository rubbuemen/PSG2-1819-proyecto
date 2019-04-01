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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Hotel;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.CauseRepository;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.samples.petclinic.repository.HotelRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClinicServiceImpl implements ClinicService {

	private PetRepository petRepository;
	private VetRepository vetRepository;
	private OwnerRepository ownerRepository;
	private VisitRepository visitRepository;
	private HotelRepository hotelRepository;
	private CauseRepository causeRepository;
	private DonationRepository donationRepository;

	@Autowired
	public ClinicServiceImpl(PetRepository petRepository, VetRepository vetRepository, OwnerRepository ownerRepository,
			VisitRepository visitRepository, HotelRepository hotelRepository, CauseRepository causeRepository, DonationRepository donationRepository) {
		this.petRepository = petRepository;
		this.vetRepository = vetRepository;
		this.ownerRepository = ownerRepository;
		this.visitRepository = visitRepository;
		this.hotelRepository = hotelRepository;
		this.causeRepository = causeRepository;
		this.donationRepository = donationRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<PetType> findPetTypes(){
		return petRepository.findPetTypes();
	}

	@Override
	@Transactional(readOnly = true)
	public Owner findOwnerById(int id){
		return ownerRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Owner> findOwnerByLastName(String lastName)  {
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	@Transactional
	public void saveOwner(Owner owner) {
		ownerRepository.save(owner);
	}

	@Override
	@Transactional
	public void saveVisit(Visit visit) {
		visitRepository.save(visit);
	}

	@Override
	@Transactional(readOnly = true)
	public Pet findPetById(int id)  {
		return petRepository.findById(id);
	}

	@Override
	@Transactional
	public void savePet(Pet pet)  {
		petRepository.save(pet);
	}

	@Override
	@Transactional
	public Collection<Vet> findVets()  {
		return vetRepository.findAll();
	}

	@Override
	public Collection<Visit> findVisitsByPetId(int petId) {
		return visitRepository.findByPetId(petId);
	}

	@Override
	@Transactional
	public void deletePet(Pet pet)  {
		petRepository.delete(pet.getId());
	}

	@Override
	@Transactional
	public void saveHotel(Hotel hotel)  {
		hotelRepository.save(hotel);
	}
	

	@Override
	public Collection<Hotel> findHotelsByPetId(int petId) {
		return hotelRepository.findByPetId(petId);
	}

	@Override
	@Transactional
	public void deleteVisit(int visitId)  {
		visitRepository.delete(visitId);
	}

	@Override
	public Visit findVisitsById(int visitId) {
		return visitRepository.findByVisitId(visitId);
	}

	@Override
	public void deleteAllVisitsByPetId(int petId)  {
		this.visitRepository.deleteAllVisitsByPetId(petId);
	}

	@Override
	@Transactional
	public void deleteHotel(int hotelId)  {
		hotelRepository.delete(hotelId);
	}

	@Override
	public Hotel findHotelById(int hotelId) {
		return hotelRepository.findByHotelId(hotelId);
	}

	@Override
	public void deleteAllHotelsByPetId(int petId)  {
		this.hotelRepository.deleteAllHotelsByPetId(petId);

	}

	@Transactional
	public void deleteOwner(Owner owner)  {
		ownerRepository.delete(owner.getId());
	}
	
    @Override
    @Transactional(readOnly = true)
	public Vet findVetById(int id)  {
		return vetRepository.findById(id);
	}
	
	@Override
	@Transactional
	public void saveVet(Vet vet)  {
		vetRepository.save(vet);
	}
	
	@Override
	@Transactional
	public void deleteVet(Vet vet)  {
		vetRepository.delete(vet.getId());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<Specialty> findVetSpecialities()  {
		return vetRepository.findVetSpecialities();
	}
	
	@Override
	@Transactional
	public void saveCause(Cause cause) {
		causeRepository.save(cause);
	}
	
	@Override
	public Cause findCauseById(int causeId) {
		return causeRepository.findByCauseId(causeId);
	}
	

	@Override
	@Transactional
	public Collection<Cause> findCauses()  {
		return causeRepository.findAll();
	}
	
	@Override
	public Donation findByDonationId(int donationId)  {
		return donationRepository.findByDonationId(donationId);
	}	


	public void saveDonation(Donation donation)  {
		donationRepository.save(donation);
	}

	@Override
	public Double totalBudget(int causeId)  {
		return causeRepository.totalBudget(causeId);
	}

	@Override
	public Collection<Donation> findDonations(int causeId)  {
		return causeRepository.findDonations(causeId);
	}

	@Override
	public List<Double> findDonationsByCauses(List<Cause> causes) {
		List<Double> res=new ArrayList<>();
		for(Cause c:causes) {
			Double res1=0.;
				for(Donation d:this.findDonationsByCauseId(c.getId())) {
					res1+=d.getAmount();
			
					}
			res.add(res1);
		}
		return res;
	}

	private Collection<Donation> findDonationsByCauseId(Integer id) {
		return donationRepository.findByCauseId(id);
	}
}
