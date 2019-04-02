package org.springframework.samples.petclinic.util;

import java.util.Collection;

import org.springframework.samples.petclinic.model.Hotel;

public class Utilidades {
	
	private Utilidades() {
		new Utilidades();
	}

	public static boolean petWithRoom(Hotel hotel, Collection<Hotel> hotels) {
		boolean ocupado = false;
		for(int i =0;i<hotels.size();i++) {
    		boolean actualStartDateBeforeNewStartDate = hotel.getPet().getHotels().get(i).getStartDate().isBefore(hotel.getStartDate());
    		boolean actualEndDateAfterNewStartDate = hotel.getPet().getHotels().get(i).getEndDate().isAfter(hotel.getStartDate());
    		boolean actualStartDateBeforeNewEndDate = hotel.getPet().getHotels().get(i).getStartDate().isBefore(hotel.getEndDate());
    		boolean actualEndDateAfterNewEndDate = hotel.getPet().getHotels().get(i).getEndDate().isAfter(hotel.getEndDate());
    		boolean actualStartDateEqualsNewStartDate = hotel.getPet().getHotels().get(i).getStartDate().equals(hotel.getStartDate());
    		boolean actualEndDateEqualsNewEndDate = hotel.getPet().getHotels().get(i).getEndDate().equals(hotel.getEndDate());
    		boolean actualStartDateAfterNewStartDate = hotel.getPet().getHotels().get(i).getStartDate().isAfter(hotel.getStartDate());
    		boolean actualEndDateBeforeNewEndDate = hotel.getPet().getHotels().get(i).getEndDate().isBefore(hotel.getEndDate());
    		
    		if((actualStartDateBeforeNewStartDate || actualStartDateEqualsNewStartDate) &&	actualEndDateAfterNewStartDate 
    			|| actualStartDateBeforeNewEndDate && (actualEndDateAfterNewEndDate || actualEndDateEqualsNewEndDate) 
    			|| (actualStartDateEqualsNewStartDate && actualEndDateEqualsNewEndDate)
    			|| (actualStartDateAfterNewStartDate && actualEndDateBeforeNewEndDate)) {
    			ocupado = true;
    		}
    	}
		return ocupado;
	}
	
	public static boolean wrongDates(Hotel hotel) {
		boolean wrongDate = false;
		if(hotel.getStartDate().isAfter(hotel.getEndDate())) {
			wrongDate = true;
		}
		return wrongDate;
	}
}
