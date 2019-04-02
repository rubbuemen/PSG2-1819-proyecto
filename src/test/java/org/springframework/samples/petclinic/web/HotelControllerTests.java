package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test class for {@link HotelController}
 *
 * @author Colin But
 */
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml"})
public class HotelControllerTests {

    private static final int TEST_PET_ID = 1;
    private static final int TEST_OWNER_ID = 1;
    private static final int TEST_HOTEL_ID = 1;

    @Autowired
    private HotelController hotelController;

    @Autowired
    private ClinicService clinicService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(hotelController).build();
		Pet pet = Mockito.mock(Pet.class);
        given(this.clinicService.findPetById(TEST_PET_ID)).willReturn(pet);
    }

    @Test
    public void testInitNewHotelForm() throws Exception {
        mockMvc.perform(get("/owners/*/pets/{petId}/hotels/new", TEST_PET_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/createOrUpdateHotelForm"));
    }

    @Test
	public void testProcessNewHotelFormSuccess() throws Exception {
		mockMvc.perform(post("/owners/*/pets/{petId}/hotels/new", TEST_PET_ID).param("details", "Hotel Details").param("startDate", "2019/10/15").param("endDate", "2019/11/15")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    public void testProcessNewHotelFormHasErrors() throws Exception {
        mockMvc.perform(post("/owners/*/pets/{petId}/hotels/new", TEST_PET_ID)
            .param("name", "George")
        )
            .andExpect(model().attributeHasErrors("hotel"))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/createOrUpdateHotelForm"));
    }

    @Test
    public void testShowHotels() throws Exception {
        mockMvc.perform(get("/owners/*/pets/{petId}/hotels", TEST_PET_ID))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("hotels"))
            .andExpect(view().name("hotelList"));
    }
    
    @Test
    public void testDeleteHotel() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/hotels/{hotelId}/delete", TEST_OWNER_ID, TEST_PET_ID, TEST_HOTEL_ID))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/{ownerId}"));
    }


}
