package com.devmigmig.spring_boot_store_demo;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootStoreDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void testShowItemForm() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("form"))
			.andExpect(model().attributeExists("item"));
	}

	@Test
	public void testSuccessfulSubmission() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.post("/submitItem")
			.param("category", "Softwaree")
			.param("name", "Microsoft")
			.param("price", "23.56")
			.param("discount", "1.67")
			.param("date", "2024-01-03");

			mockMvc.perform(request)
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/inventory"));
	}

	@Test
	public void testGetItems() throws Exception{
		RequestBuilder request =  MockMvcRequestBuilders.get("/inventory");

		mockMvc.perform(request)
			.andExpect(status().is2xxSuccessful())
			.andExpect(view().name("inventory"))
			.andExpect(model().attributeExists("items"));
	}

}
