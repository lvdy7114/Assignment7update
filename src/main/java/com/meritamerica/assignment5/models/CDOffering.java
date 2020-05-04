package com.meritamerica.assignment5.models;

public class CDOffering {
	
	
	private int term;
	private double interestRate;
	
	CDOffering(int term, double interestRate) {
		//constructs the CDOffering
		this.term = term;
		this.interestRate = interestRate;
				
	}
	
	int getTerm() {
		return term;
	}
	
	double getInterestRate() {
		return interestRate;
	}
	
	static CDOffering readFromString(String cdOfferingDataString) {
		
		CDOffering toBeAdded = null;
		try{
			String[] toBeParsed = cdOfferingDataString.split(",");
			int termToAdd = Integer.parseInt(toBeParsed[0]);
			double interestRateToBeAdded = Double.parseDouble(toBeParsed[1]);
			
			toBeAdded = new CDOffering(termToAdd , interestRateToBeAdded);
			
			
			
			
		}catch(NumberFormatException exception) {
			throw exception;		
		}
		
		return toBeAdded;
		
	}
	
	
	public String writeToString() {
		StringBuilder cdoStringBuilder = new StringBuilder(term + "," + interestRate);
		String toString = cdoStringBuilder.toString();
		return toString;
		
	}

	
	
	
}
