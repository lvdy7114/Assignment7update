package com.meritamerica.assignment5.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CheckingAccount extends BankAccount {
	
	public static final double checkingInterestRate = 0.0001;
	
	public CheckingAccount() {
		super(checkingInterestRate);
	}
	
	
	public CheckingAccount(double startBalance){
		
		super(startBalance , checkingInterestRate);
	}
	
    public CheckingAccount(double startBalance , double interestRate , long accountNumber , java.util.Date startDate) {
		
		super(accountNumber , startBalance , interestRate , startDate);
		
	}
	
	static CheckingAccount readFromString(String accountData) {
		
		CheckingAccount toBeAdded = null;
		try{
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			String[] toBeParsed = accountData.split(",");
			long accountNumberToAdd = Long.parseLong(toBeParsed[0]);
			double curentBalanceToBeAdded = Double.parseDouble(toBeParsed[1]);
			double interestRateToBeAdded = Double.parseDouble(toBeParsed[2]);
			java.util.Date dateToBeAdded = dateFormatter.parse(toBeParsed[3]);
			
			
			toBeAdded = new CheckingAccount(curentBalanceToBeAdded , interestRateToBeAdded , accountNumberToAdd , dateToBeAdded);
		
		
		}catch(NumberFormatException exception) {
			throw exception;
			
		}catch(ParseException exception) { 
			
		}
		
		return toBeAdded;
		
	}
	

		

	
}
