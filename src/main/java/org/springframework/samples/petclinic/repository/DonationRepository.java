package org.springframework.samples.petclinic.repository;


import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Donation;

public interface DonationRepository {
	
	void save(Donation donation) throws DataAccessException;
    
	@Query("SELECT d FROM Donation d where d.id=:donationId")
	Donation findByDonationId(@Param(value = "donationId") int donationId) throws DataAccessException;


}
