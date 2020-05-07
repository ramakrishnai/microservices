package com.javapractice.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeValue {

	@Id
	private Long id;
	
	@Column(name="cfrom")
	private String cfrom;
	
	@Column(name="cto")
	private String cto;
	
	private BigDecimal conversionMultiple;
	private int port;
	

	public ExchangeValue() {
		
	}
	
	
	public ExchangeValue(Long id, String cfrom, String cto, BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.cfrom = cfrom;
		this.cto = cto;
		this.conversionMultiple = conversionMultiple;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCfrom() {
		return cfrom;
	}

	public void setCfrom(String cfrom) {
		this.cfrom = cfrom;
	}

	public String getCto() {
		return cto;
	}

	public void setCto(String cto) {
		this.cto = cto;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	
	
	
}
