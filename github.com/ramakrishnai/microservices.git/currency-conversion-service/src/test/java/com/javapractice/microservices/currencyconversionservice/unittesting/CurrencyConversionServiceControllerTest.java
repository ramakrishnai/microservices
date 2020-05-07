package com.javapractice.microservices.currencyconversionservice.unittesting;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.client.RestTemplate;

import com.javapractice.microservices.currencyconversionservice.CurrencyConversionBean;
import com.javapractice.microservices.currencyconversionservice.CurrencyConversionController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CurrencyConversionServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CurrencyConversionController controller;
	
	@LocalServerPort
    int randomServerPort;
	
	@Mock
	private RestTemplate restTemplate;
	
	private WebTestClient restWebClient;
	
	
	@Test
	public void currencyConversionTest(){
		
		String URI = "/currency-converter/from/{cfrom}/to/{cto}/bucks/{bucks}";
		String currencyExchangeURL = "http://localhost:8000/currency-exchange/from/USD/to/INR";
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("cfrom", "USD");
		uriVariables.put("cto", "INR");
		
		//Stub the object which is expected from controllers as we don't want to call database to pull data.
		CurrencyConversionBean returnSubvalue = new CurrencyConversionBean(10001L, "USD", "INR", 
				BigDecimal.valueOf(65), BigDecimal.valueOf(2000), 
				BigDecimal.valueOf(65).multiply(BigDecimal.valueOf(2000)), 0);
		
		try {
		Mockito.when(restTemplate.getForObject(currencyExchangeURL, CurrencyConversionBean.class, uriVariables)).thenReturn(returnSubvalue);
		RequestBuilder requestBuilder = get(URI, "USD", "INR", BigDecimal.valueOf(2000)).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
			
		//this.restWebClient.get().uri(URI, "USD", "INR", BigDecimal.valueOf(65)).exchange().expectStatus().isOk();
		
		CurrencyConversionBean response = controller.convertCurrency("USD", "INR", BigDecimal.valueOf(2000));
		
		} catch(IOException io) {
			io.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		/*assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    assertThat(response.getBody()).isEqualTo(dto);*/
		
		
	}
	
	
	
	@Test
	public void testGetCurrencyTotal() throws Exception 
	{
	    RestTemplate restTemplate = new RestTemplate();
	    String currencyExchangeServiceMapping = "/currency-exchange/from/{cfrom}/to/{cto}";
	    Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("cfrom", "USD");
		uriVariables.put("cto", "INR");
	     
	    final String baseUrl = "http://localhost:" + randomServerPort + currencyExchangeServiceMapping;
	    CurrencyConversionBean response = restTemplate.getForObject(baseUrl, CurrencyConversionBean.class, uriVariables);
	    
	    Assert.assertEquals(BigDecimal.valueOf(65).multiply(BigDecimal.valueOf(2000)), response.getConversionMultiple());
	    
	}
	
	
}
