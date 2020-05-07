package com.javapractice.microservices.currencyexchangeservice.controller;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.javapractice.microservices.currencyexchangeservice.CurrencyExchangeController;
import com.javapractice.microservices.currencyexchangeservice.ExchangeValue;
import com.javapractice.microservices.currencyexchangeservice.ExchangeValueRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyExchangeController.class)
public class CurrencyExchangeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ExchangeValueRepository repository;
	
	
	@Test
	public void currencyExchangeTest() throws Exception {
		
		String URI = "/currency-exchange/from/{cfrom}/to/{cto}";
		
		//Stub the object which is expected from controllers as we don't want to call database to pull data. Thats is separate Test Case. 
		ExchangeValue exchangeValue = new ExchangeValue(1000L, "USD", "INR", BigDecimal.valueOf(65));
		
		Optional<ExchangeValue> stubValue = Optional.of(exchangeValue);
		when(repository.findByCfromAndCto("USD", "INR")).thenReturn(stubValue);	
		
		RequestBuilder requestBuilder = get(URI, "USD", "INR").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(jsonPath("conversionMultiple").value(BigDecimal.valueOf(65))).andReturn();
		
		assertEquals(200, result.getResponse().getStatus());
	
	}
	
}
