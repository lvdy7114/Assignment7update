package com.meritamerica.assignment5.models;

@SuppressWarnings("serial")
public class ExceedsCombinedBalanceLimitException extends Exception {
	
	static final double combinedBalanceLimit = 250000;
	
	public ExceedsCombinedBalanceLimitException() {}
	
	public ExceedsCombinedBalanceLimitException (String message) {
		super(message);
	}

	public static double getCombinedbalancelimit() {
		return combinedBalanceLimit;
	}
	
	

}
