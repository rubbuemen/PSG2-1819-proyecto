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
package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Hotel;
import org.springframework.transaction.annotation.Transactional;

public interface HotelRepository {

    void save(Hotel hotel) throws DataAccessException;

    List<Hotel> findByPetId(Integer hotelId);
    
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Hotel h where h.id=:hotelId")
    void delete(@Param(value = "hotelId") int hotelId) throws DataAccessException;
    
    @Query("SELECT v FROM Hotel v where v.id=:hotelId")
	Hotel findByHotelId(@Param(value = "hotelId") int hotelId) throws DataAccessException;
    
    @Query("SELECT p.hotels FROM Pet p where p.id=:petId")
	Collection<Hotel> findHotelsByPetId(@Param(value = "petId") int petId) throws DataAccessException;

	@Transactional
	@Modifying
	@Query("DELETE FROM Hotel v where v.pet.id=:petId")
	void deleteAllHotelsByPetId(@Param(value = "petId") int petId) throws DataAccessException;

}
