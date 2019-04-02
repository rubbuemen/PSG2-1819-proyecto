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
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test class for the {@link DonationController}
 *
 * @author Colin But
 */
@SpringJUnitWebConfig(locations = { "classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml" })
public class DonationControllerTests {

	private static final int TEST_CAUSE_ID = 1;

	@Autowired
	private DonationController donationController;

	@Autowired
	private ClinicService clinicService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(donationController).build();
		Cause cause = Mockito.mock(Cause.class);
		given(this.clinicService.findCauseById(TEST_CAUSE_ID)).willReturn(cause);
	}

	@Test
	public void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/causes/{causeId}/donations/new", TEST_CAUSE_ID)).andExpect(status().isOk())
				.andExpect(view().name("donations/createOrUpdateDonationForm"))
				.andExpect(model().attributeExists("donation"));
	}
	
	@Test
	public void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/causes/{causeId}/donations/new", TEST_CAUSE_ID).param("isClosed", "false").param("budgetTarget", "2000.0").param("amount", "20.0")
				.param("date", "2015/02/12").param("client", "test")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/causes"));
	}
	
	@Test
	public void testProcessCreationFormErrors() throws Exception {
		mockMvc.perform(post("/causes/{causeId}/donations/new", TEST_CAUSE_ID)).andExpect(status().isOk())
				.andExpect(view().name("donations/createOrUpdateDonationForm"));
	}


}
