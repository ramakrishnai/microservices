package com.javapractice.microservices.currencyexchangeservice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	
	/*
	 * Spring Data Jpa provide implementation for Query methods using Entity ExchangeValue properties
	 * When you provide method name: findByXXXXXAndXXX() where XXXXX -> Cfrom and XXX -> Cto to be appended.
	 * spring-data-jpa find out Table name through ExchangeValueRepository and find 
	 * column names for the given params(cfrom, cto) within the table Exchange_Value. 
	 */
	
	public Optional<ExchangeValue> findByCfromAndCto(String cfrom, String cto);

}
