package com.finance.rksamples.fincalmicroservice.model;

public class Item {

	private int id;
	private String name;
	private int print;
	private int quantity;
	
	public Item() {
		
	}
	
	public Item(int id, String name, int print, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.print = print;
		this.quantity = quantity;
	}
	
	
	
	@Override
	public String toString() {
		return String.format("Item [id=%s, name=%s, print=%s, quantity=%s]", id, name, print, quantity);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrint() {
		return print;
	}
	public void setPrint(int print) {
		this.print = print;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
