package com.javapractice.microservices.currencyexchangeservice;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExchangeValueRepository repository;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{cfrom}/to/{cto}")
	public ExchangeValue retrieveExchangeValue(
			@PathVariable String cfrom, 
			@PathVariable String cto) {
		logger.info("CurrencyExchangeController --------> retrieveExchangeValue()");
		//ExchangeValue exchangeValue = new ExchangeValue(1000L, "USD", "INR", BigDecimal.valueOf(65));
		Optional<ExchangeValue> exchangeValue = repository.findByCfromAndCto(cfrom, cto);
		//exchangeValue.get().setPort(Integer.parseInt(environment.getProperty("local.server.port"))); //Commented for Junit Testing due to NumberFormatException
		
		
		logger.info("{}", exchangeValue.get());
		return exchangeValue.get();
		
	}
}
