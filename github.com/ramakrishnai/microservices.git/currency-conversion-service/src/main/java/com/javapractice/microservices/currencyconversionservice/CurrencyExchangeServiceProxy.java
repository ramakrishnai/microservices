package com.javapractice.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name="currency-exchange-service", url="localhost:8000")
//@FeignClient(name="currency-exchange-service") --> See below for Zuul API Gateway


@FeignClient(name="netflix-zuul-api-gateway-server")//Calls to sub-microservices should go through Zuul API Gateway
@RibbonClient(name="currency-exchange-service")//Load Balancing when exchange-service running on multiple instances
public interface CurrencyExchangeServiceProxy {
	
	/*//Zuul code below so commented
	@GetMapping("/currency-exchange/from/{cfrom}/to/{cto}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("cfrom") String cfrom, 
			@PathVariable("cto") String cto);
	*/
	
	//Using Zuul API Gateway Server calling RestController using below GetMapping with Zuul Host and Port -> http://localhost:8765/URI_from_Proxy
	@GetMapping("/currency-exchange-service/currency-exchange/from/{cfrom}/to/{cto}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("cfrom") String cfrom, 
			@PathVariable("cto") String cto);
	
	
	//All other RESTful method calls from Currency-Conversion-Service-Application and ExchangeServiceProxy will be written here. 
	
	//If you need any other microservices to call.. say for eg: Micro-Service-Xyz calls another Microservice then create XyzProxy.java same as above. 

}
