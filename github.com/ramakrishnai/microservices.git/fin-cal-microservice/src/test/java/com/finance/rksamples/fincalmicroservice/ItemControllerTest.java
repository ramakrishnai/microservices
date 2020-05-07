package com.finance.rksamples.fincalmicroservice;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.finance.rksamples.fincalmicroservice.controller.ItemController;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAllItems_basic_one() throws Exception {
		
		
		  RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item")
		  .accept(MediaType.APPLICATION_JSON);
		  
		  MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		  assertEquals(200, result.getResponse().getStatus());
		 	
		
		//or
		
		/*
		mockMvc.perform(MockMvcRequestBuilders.get("/dummy-item"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("name").value("Ball"))
						.andExpect(jsonPath("print").value(10))
						.andExpect(jsonPath("quantity").value(10));   */
						
				
	}
	
	@Test
	public void getAllItems_basic_two() throws Exception {
		
		
		  RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item")
		  .accept(MediaType.APPLICATION_JSON);
		  
		  MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		 	
		  assertEquals(200, result.getResponse().getStatus());
		
				
	}
	
	
	

}
