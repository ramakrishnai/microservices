package com.finance.rksamples.fincalmicroservice.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.finance.rksamples.fincalmicroservice.model.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello, Welcome Microservice Tutorials";
	}
	
	
	
	@GetMapping(path="/hello-world-i18n")
	public String helloWorldI8n(Locale locale) {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
	
	
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Welcome to Microservices World");
	}
	
	
	
	
	//Path-Variable: hello-world/rama
	@GetMapping(path="/hello-world/{username}")
	public String helloWorld(@PathVariable String username) {
		return "Hello *** "+username +" ***, Welcome Microservice Tutorials";
	}
	
	
}
