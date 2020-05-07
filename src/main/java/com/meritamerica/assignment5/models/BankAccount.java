package com.meritamerica.assignment5.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;



//import java.text.ParseException;
//import java.text.SimpleDateFormat;

public abstract class BankAccount {
	
		
	@Min(value = 1 , message = "balance size error too small")
	@Max(value = 25000 , message = "balance size error too big")	
	private double balance;
	
		
	//private double futureBalance;
	private long accountNumber;
	private java.util.Date openedOn;
	private List<Transaction> transactionList;
	
	
	public BankAccount() {
		this.accountNumber = MeritBank.getNextAccountNumber();		
		this.openedOn = new java.util.Date();		
	}
	
	public BankAccount(double balance) {
		
		this.balance = balance;
		this.accountNumber = MeritBank.getNextAccountNumber();
		this.openedOn = new java.util.Date();
		
		
	}
	
	public BankAccount(double balance, java.util.Date accountOpenedOn) {
		
		this.balance = balance;		
		this.accountNumber = MeritBank.getNextAccountNumber();
		this.openedOn = accountOpenedOn;
		
	}
	
	public BankAccount(long accountNumber, double balance, java.util.Date accountOpenedOn) {
		
		this.balance = balance;		
		this.accountNumber = accountNumber;
		this.openedOn = accountOpenedOn;
		
	}
	
	
	
	
	

  //setters and getters 
	public java.util.Date getOpenedOn() {
		return openedOn;
	}
	
	public void setOpenedOn(java.util.Date date) {
		this.openedOn = date;
	}
	
	public void setAccountNumber(long theAccountNumber) {
		this.accountNumber = theAccountNumber;
	}	
	
	public long getAccountNumber() {
		return accountNumber;		
	}
	
	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	//methods
	boolean withdraw(double amount) {
		if (getBalance() >= amount) {
			setBalance(getBalance()-amount);
			return true;
		}else{
			return false;
		}
			
	}
	
	boolean deposit(double amount) {
		if(amount > 0){
		setBalance(getBalance()+amount);
		return true;
		}
		return false;
		
	}
	
	boolean transfer(BankAccount from , BankAccount to , double amount) {
		
		
		
		return false;
	}
	
	public abstract double getInterestRate();
		
	public String toString() {
		return ("");
	}

	public double futureValue(int years) {
		return MeritBank.recursiveFutureValue(balance, years, getInterestRate());
					
	}
	
	public String writeToString() {
		StringBuilder sb = new StringBuilder(accountNumber + "," + balance + "," + getInterestRate() + "," + openedOn + "/n");
		
		int numberOfTransactions = transactionList.size();
		sb.append(numberOfTransactions + "/n");
		while(!transactionList.isEmpty()){
			sb.append(transactionList.dequeue() + "/n");
		}
		
		String toBeReturned = sb.toString();
		return toBeReturned;
	}
	
	public void addTransaction(Transaction transaction) {
		
		if(transactionList == null){
			Node<Transaction> n = new Node<Transaction>(transaction);
			transactionList = new List<Transaction>(n);
			
		}else {
			transactionList.enqueue(transaction);
		}
				
	}
	
	public List<Transaction> getTransactions() {
		return transactionList;
		
	}

	
	
	
	
	
	
	
	/*static BankAccount readFromString(String accountData) {
		
		BankAccount toBeAdded = null;
		try{
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			String[] toBeParsed = accountData.split(",");
			long accountNumberToAdd = Integer.parseInt(toBeParsed[0]);
			double curentBalanceToBeAdded = Double.parseDouble(toBeParsed[1]);
			double interestRateToBeAdded = Double.parseDouble(toBeParsed[2]);
			java.util.Date dateToBeAdded = dateFormatter.parse(toBeParsed[3]);
			
			
			toBeAdded = new BankAccount(accountNumberToAdd , curentBalanceToBeAdded , interestRateToBeAdded , dateToBeAdded);
		
		
		}catch(ParseException | NumberFormatException exception) {
						
		}
		
		return toBeAdded;
		
	}*/
	
}
