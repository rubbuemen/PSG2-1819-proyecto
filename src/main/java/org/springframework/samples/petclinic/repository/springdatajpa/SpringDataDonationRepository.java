package org.springframework.samples.petclinic.repository.springdatajpa;


import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;

public interface SpringDataDonationRepository extends DonationRepository, Repository<Donation, Integer>{
	
}
