package com.meritamerica.assignment5.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CDAccount extends BankAccount {
	
	private int term;
	
	
	public CDAccount(CDOffering offering , double openingBalance) {
		super(openingBalance , offering.getInterestRate());
		this.term = offering.getTerm();
	}
	
	public CDAccount(double startBalance , double interestRate , long accountNumber , java.util.Date startDate , int termToBeAdded) {
		
		super(accountNumber , startBalance , interestRate , startDate);
		this.term = termToBeAdded;
	}
	
	public int getTerm() {
		return term;
	}
	
	
     public double futureValue() {
	    return super.futureValue(term);
			
     }
     
     boolean withdraw(double amount) {
    	 return false;
     }
     
     boolean deposit(double amount) {
    	 return false;
     }
     
     static CDAccount readFromString(String accountData) {
 		
    	 CDAccount toBeAdded = null;
 		try{
 			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
 			String[] toBeParsed = accountData.split(",");
 			long accountNumberToAdd = Integer.parseInt(toBeParsed[0]);
 			double curentBalanceToBeAdded = Double.parseDouble(toBeParsed[1]);
 			double interestRateToBeAdded = Double.parseDouble(toBeParsed[2]);
 			java.util.Date dateToBeAdded = dateFormatter.parse(toBeParsed[3]);
 			int termToBeAdded = Integer.parseInt(toBeParsed[4]);
 			
 			toBeAdded = new CDAccount(curentBalanceToBeAdded , interestRateToBeAdded , accountNumberToAdd , dateToBeAdded , termToBeAdded);
 		
 		
 		}catch(NumberFormatException exception) {
			throw exception;
			
		}catch(ParseException exception) { 
			
		}
 		
 		return toBeAdded;
 		
 	} 
    	 //throws ParseException
    	 //Should throw a java.lang.NumberFormatException if String cannot be correctly parsed

     
     
     @Override 
     public String writeToString() {
 		StringBuilder sb = new StringBuilder(getAccountNumber() + "," + getBalance() + "," + term + "," + getInterestRate() + "," + getOpenedOn());
 		
 		int numberOfTransactions = super.getTransactions().size();
		sb.append(numberOfTransactions + "/n");
		while(!getTransactions().isEmpty()){
			sb.append(getTransactions().dequeue() + "/n");
		}
 		
 		String toBeReturned = sb.toString();
 		return toBeReturned;
 	}
     
}



