package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity{
	
	@NotNull
	@Column(name = "amount")
	@Min(0)
	private Double amount;
	
    @NotNull
    @Column(name =  "date_of_donation")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate date;
	
    @NotBlank
    @Column(name="client")
    private String client;
    
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
	
    @ManyToOne
    @JoinColumn(name = "cause_id")
    private Cause cause;
    
    public Cause getCause() {
        return this.cause;
    }

    public void setCause(Cause cause) {
        this.cause = cause;
    }

}
