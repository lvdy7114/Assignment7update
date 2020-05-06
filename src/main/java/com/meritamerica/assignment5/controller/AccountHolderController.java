package com.meritamerica.assignment5.controller;



import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.Exceptions.NoSuchResorceFoundException;
import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.BankAccount;
import com.meritamerica.assignment5.models.CDAccount;
import com.meritamerica.assignment5.models.CDOffering;
import com.meritamerica.assignment5.models.CheckingAccount;
import com.meritamerica.assignment5.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.models.MeritBank;
import com.meritamerica.assignment5.models.SavingsAccount;

@RestController
public class AccountHolderController {
	
	//MeritBank m = new MeritBank();
	
	
  	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String test() {
		return "hello world";
	}
  	
  	
  	@GetMapping(value = "/AccountHolders")
  	public AccountHolder[] getAccountHolders() {
  		return MeritBank.getAccountHolders();
  	}

  	@PostMapping(value = "/AccountHolders")
  	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder postAccounHolder(@RequestBody @Valid AccountHolder a) {				
  			MeritBank.addAccountHolder(a);
  			return a;	
  	}
  	
	@GetMapping(value = "/AccountHolders/{id}")
	public @ResponseBody AccountHolder getAccountHolderBytheId(@PathVariable int id) throws NoSuchResorceFoundException {
		return MeritBank.GetAccountHolderById(id);
		
	}
  	
	@PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount[] addCheckingAccountsToId(@RequestBody CheckingAccount c , @PathVariable int id) throws NoSuchResorceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		a.addCheckingAccount(c.getBalance()); 
		return a.getBankAccounts();
	}  
			
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public @ResponseBody CheckingAccount[] getCheckingAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		return a.getTheCheckingAccounts(a);
	}
	
	
	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount[] addSavingsAccountsToId(@RequestBody CheckingAccount c , @PathVariable int id) throws NoSuchResorceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		a.addSavingsAccount(c.getBalance()); 
		return a.getBankAccounts();
	}
	
			
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public @ResponseBody SavingsAccount[] getSavingsAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		return a.getTheSavingsAccounts(a);
	}
	
	
	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount[] addCDAccountsToId(@RequestBody CDAccount c , @PathVariable int id) throws NoSuchResorceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		a.addSavingsAccount(c.getBalance()); 
		return a.getBankAccounts();
	}
	
			
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	public @ResponseBody CDAccount[] getCDAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		return a.getTheCDAccounts(a);
	}
	
		
	@PostMapping(value = "/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering addCDOffering(@RequestBody CDOffering c) {
		addCDOffering(c);
		return c;
	}
	
	
	@GetMapping(value = "/CDAccounts")
	public CDOffering[] getCDOfferings() {
		return MeritBank.getCDOfferings();
	}
	/*
	CDOffering:
		POST /CDOfferings
		Creates a new CD Offering
		GET /CDOfferings
		Returns all CD Offerings
*/
	

}
