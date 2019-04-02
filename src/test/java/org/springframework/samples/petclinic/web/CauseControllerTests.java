package org.springframework.samples.petclinic.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test class for {@link CauseController}
 *
 * @author Colin But
 */
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml"})
public class CauseControllerTests {

    private static final int TEST_CAUSE_ID = 1;

    @Autowired
    private CauseController causeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(causeController).build();
    }

    @Test
    public void testInitNewCauseForm() throws Exception {
        mockMvc.perform(get("/causes/new", TEST_CAUSE_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("causes/createOrUpdateCauseForm"));
    }

    @Test
	public void testProcessNewCauseFormSuccess() throws Exception {
		mockMvc.perform(post("/causes/new", TEST_CAUSE_ID).param("name", "George").param("description", "Cause description").param("budgetTarget", "20.0").param("organization", "Cause organization")
        )
            .andExpect(status().isOk())
            .andExpect(view().name("causes/createOrUpdateCauseForm"));
    }

    @Test
    public void testProcessNewCauseFormHasErrors() throws Exception {
        mockMvc.perform(post("/causes/new", TEST_CAUSE_ID)
            .param("name", "George")
        )
            .andExpect(model().attributeHasErrors("cause"))
            .andExpect(status().isOk())
            .andExpect(view().name("causes/createOrUpdateCauseForm"));
    }
    
    @Test
    public void testShowCauses() throws Exception {
    	 mockMvc.perform(get("/causes"))
         .andExpect(status().isOk())
         .andExpect(view().name("causes/causeList"));
    }

}
