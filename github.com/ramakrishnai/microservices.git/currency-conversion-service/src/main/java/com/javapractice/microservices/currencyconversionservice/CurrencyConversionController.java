package com.javapractice.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	//URL -> http://localhost:8100/currency-converter/from/USD/to/INR/bucks/100
	@GetMapping("/currency-converter/from/{cfrom}/to/{cto}/bucks/{bucks}")
	public CurrencyConversionBean convertCurrency(
			@PathVariable String cfrom,
			@PathVariable String cto,
			@PathVariable BigDecimal bucks) {
		
		logger.info("CurrencyConversionController -> convertCurrency()");
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("cfrom", cfrom);
		uriVariables.put("cto", cto);
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{cfrom}/to/{cto}", 
				CurrencyConversionBean.class,
				uriVariables);
		
		CurrencyConversionBean response = responseEntity.getBody();
		logger.info("{}", response);
		return(new CurrencyConversionBean(response.getId(), cfrom, cto, 
				response.getConversionMultiple(), bucks, 
				bucks.multiply(response.getConversionMultiple()), 
				response.getPort()));
	}
	
	
	
	//Using Feign calling microservices
	//URL -> http://localhost:8100/currency-converter/from/USD/to/INR/bucks/100
	@GetMapping("/currency-converter-feign/from/{cfrom}/to/{cto}/bucks/{bucks}")
	public CurrencyConversionBean convertCurrencyFeign(
			@PathVariable String cfrom,
			@PathVariable String cto,
			@PathVariable BigDecimal bucks) {
		
		logger.info("CurrencyConversionController -> convertCurrencyFeign()");
		
		CurrencyConversionBean response = proxy.retrieveExchangeValue(cfrom, cto);
		logger.info("{}", response);
		return(new CurrencyConversionBean(response.getId(), cfrom, cto, 
				response.getConversionMultiple(), bucks, 
				bucks.multiply(response.getConversionMultiple()), 
				response.getPort()));
	}


}
