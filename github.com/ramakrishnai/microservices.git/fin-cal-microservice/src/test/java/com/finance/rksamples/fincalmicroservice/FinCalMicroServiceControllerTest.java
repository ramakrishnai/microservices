package com.finance.rksamples.fincalmicroservice;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import com.finance.rksamples.fincalmicroservice.controller.HelloWorldController;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class FinCalMicroServiceControllerTest {

	@Autowired
	private MockMvc mocMvc;
	
	@Test
	public void helloWorld_basic() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/hello-world").accept(MediaType.APPLICATION_JSON);
		//MvcResult result = mocMvc.perform(request).andReturn();
		MvcResult result = mocMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string("Hello, Welcome Microservice Tutorials"))
				.andReturn();
		
		
		//Verify the results of REST API response
		assertEquals("Hello, Welcome Microservice Tutorials", result.getResponse().getContentAsString());
		
	}
}
