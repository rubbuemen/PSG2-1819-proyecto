package org.springframework.samples.petclinic.repository;


import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Donation;

public interface DonationRepository {
	
	void save(Donation donation) throws DataAccessException;
    
	@Query("SELECT d FROM Donation d where d.id=:donationId")
	Donation findByDonationId(@Param(value = "donationId") int donationId) throws DataAccessException;
	
	@Query("SELECT d FROM Donation d where d.cause.id=:causeId")
	Collection<Donation> findByCauseId(@Param(value = "causeId") int causeId)throws DataAccessException;


}
