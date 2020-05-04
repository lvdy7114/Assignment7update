package com.meritamerica.assignment5.models;

@SuppressWarnings("serial")
public class ExceedsAvailableBalanceException extends Exception {
	
	public ExceedsAvailableBalanceException() {}
	
	public ExceedsAvailableBalanceException(String message) {
		super(message);
	}

}