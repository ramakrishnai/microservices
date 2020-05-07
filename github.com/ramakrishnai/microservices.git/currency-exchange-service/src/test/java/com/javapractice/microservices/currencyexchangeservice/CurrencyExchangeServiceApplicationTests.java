package com.javapractice.microservices.currencyexchangeservice;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyExchangeServiceApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private ExchangeValueRepository repository;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testSaveExchangeValues() throws Exception{
		
		//String jsonRequest = mapper.writeValueAsString(exchangeValue);
		
		String URI = "/currency-exchange/from/{cfrom}/to/{cto}";
		
		//Stub the object which is expected from controllers as we don't want to call database to pull data. Thats is separate Test Case. 
		ExchangeValue exchangeValue = new ExchangeValue(1000L, "USD", "INR", BigDecimal.valueOf(65));
		//exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		Optional<ExchangeValue> stubValue = Optional.of(exchangeValue);
		
		//Mockito.when(repository.findByCfromAndCto("USD", "INR")).thenReturn(stubValue);	
		RequestBuilder requestBuilder = get(URI, "USD", "INR").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		
		assertEquals(200, result.getResponse().getStatus());
		
	}

}
