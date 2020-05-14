package com.meritamerica.assignment5.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class CheckingAccount extends BankAccount {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private double interestRate;
	
	public static final double checkingInterestRate = 0.0001;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_holder_id" , referencedColumnName = "id")
	private AccountHolder accountHolder;
	
	public CheckingAccount() {
		super();
		this.interestRate = checkingInterestRate;
		this.id = id;
		
	}
	
	
	public CheckingAccount(double startBalance){
		super(startBalance);
		this.interestRate = checkingInterestRate;		
	}
	
    public CheckingAccount(double startBalance , double interestRate , long accountNumber , java.util.Date startDate) {
		
		super(accountNumber , startBalance , startDate);
		this.interestRate = interestRate;		
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

	

	public AccountHolder getAccountHolder() {
		return accountHolder;
	}


	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public double getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}


	public static double getCheckinginterestrate() {
		return checkingInterestRate;
	}
	

		

	
}
