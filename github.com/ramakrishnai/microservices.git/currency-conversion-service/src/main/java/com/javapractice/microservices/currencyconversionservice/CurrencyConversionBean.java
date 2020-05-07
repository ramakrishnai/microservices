package com.javapractice.microservices.currencyconversionservice;

import java.math.BigDecimal;

public class CurrencyConversionBean {

	
	private Long id;
	private String cfrom;
	private String cto;
	private BigDecimal conversionMultiple;
	private BigDecimal bucks;
	private BigDecimal totalAmount;
	private int port;
	
	public CurrencyConversionBean() {
		
	}

	public CurrencyConversionBean(Long id, String cfrom, String cto, BigDecimal conversionMultiple, BigDecimal bucks,
			BigDecimal totalAmount, int port) {
		super();
		this.id = id;
		this.cfrom = cfrom;
		this.cto = cto;
		this.conversionMultiple = conversionMultiple;
		this.bucks = bucks;
		this.totalAmount = totalAmount;
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

	public BigDecimal getBucks() {
		return bucks;
	}

	public void setBucks(BigDecimal bucks) {
		this.bucks = bucks;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	
}
