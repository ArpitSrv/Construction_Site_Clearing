package com.oracle.assignment.construction.utility;


/**
 * Wrapper class to wrap Units and Costs against Operation names
 * 
 * @author Arpit Srivastava
 */

public class WrapperOfUnitAndCost {
	
	private int qty;
	private int price;
	private String header;

	public WrapperOfUnitAndCost(String header, int qty, int price) {
		this.header = header;
		this.qty = qty;
		this.price = price;
	}

	public int getUnit() {
		return this.qty;
	}

	public int getCost() {
		return this.price;
	}

	public String getHeader() {
		return this.header;
	}
}
