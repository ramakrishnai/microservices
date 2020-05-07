package com.finance.rksamples.fincalmicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.rksamples.fincalmicroservice.model.Item;

@RestController
public class ItemController {
	
	@GetMapping("/dummy-item") 
	public Item getItems() {
		System.err.println("Junit Test call from ItemControllerTest with URI --------> /dummy-item");
		return new Item(1, "Ball", 10, 10);
	}

}
