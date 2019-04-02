package org.springframework.samples.petclinic.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.samples.petclinic.model.Hotel;

public class Utilidades {
	
	private Utilidades() {
		new Utilidades();
	}

	public static boolean petWithRoom(Hotel hotel, Collection<Hotel> hotels) {
		boolean ocupado = false;
		List<Hotel> hotelsOfAPet = new ArrayList<>();
		hotelsOfAPet.addAll(hotels);
		for(int i =0;i<hotels.size();i++) {
			LocalDate actualStartDate = hotelsOfAPet.get(i).getStartDate();
			LocalDate actualEndDate = hotelsOfAPet.get(i).getEndDate();
			LocalDate newStartDate = hotel.getStartDate();
			LocalDate newEndDate = hotel.getEndDate();
    		boolean actualStartDateBeforeNewStartDate = actualStartDate.isBefore(newStartDate);
    		boolean actualEndDateAfterNewStartDate = actualEndDate.isAfter(newStartDate);
    		boolean actualStartDateBeforeNewEndDate = actualStartDate.isBefore(newEndDate);
    		boolean actualEndDateAfterNewEndDate = actualEndDate.isAfter(newEndDate);
    		boolean actualStartDateEqualsNewStartDate = actualStartDate.equals(newStartDate);
    		boolean actualEndDateEqualsNewEndDate = actualEndDate.equals(newEndDate);
    		boolean actualStartDateAfterNewStartDate = actualStartDate.isAfter(newStartDate);
    		boolean actualEndDateBeforeNewEndDate = actualEndDate.isBefore(newEndDate);
    		
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
