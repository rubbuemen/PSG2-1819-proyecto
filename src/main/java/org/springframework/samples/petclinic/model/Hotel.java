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
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 */
@Entity
@Table(name = "hotels")
public class Hotel extends BaseEntity {

    @NotBlank
    @Column(name = "details")
    private String details;
    
    @NotNull
    @Column(name = "start_date_book")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;
    
    @NotNull
    @Column(name = "end_date_book")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate endDate;
    
    public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endtDate) {
		this.endDate = endtDate;
	}

	@ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

    

}
