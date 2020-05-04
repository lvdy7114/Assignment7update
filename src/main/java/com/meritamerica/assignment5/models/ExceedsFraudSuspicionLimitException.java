package com.meritamerica.assignment5.models;

@SuppressWarnings("serial")
public class ExceedsFraudSuspicionLimitException extends Exception {
	
	public ExceedsFraudSuspicionLimitException() {
		
	}
		
	public ExceedsFraudSuspicionLimitException(String message) {
		super(message);
	}
	
	
	public String toString() {
		return "Are you with the mob?";
	}
	
	
}
