package com.meritamerica.assignment5.controller;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.meritamerica.assignment5.models.AccountHoldersContactDetails;
import com.meritamerica.assignment5.models.CDAccount;
import com.meritamerica.assignment5.models.CDOffering;
import com.meritamerica.assignment5.models.CheckingAccount;
import com.meritamerica.assignment5.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.models.MeritBank;
import com.meritamerica.assignment5.models.SavingsAccount;
import com.meritamerica.assignment5.repositories.AccountHolderRepository;
import com.meritamerica.assignment5.repositories.AccountHoldersContactDetailsRepository;



@RestController
@RequestMapping("/rest/accountholders")
public class AccountHolderController {
	
	@Autowired
	AccountHolderRepository accrepo;
	AccountHoldersContactDetailsRepository ahcdrepo;
	
	

	@GetMapping("/ContactDetails")
	public List<AccountHoldersContactDetails> getContactDetails() {
		return ahcdrepo.findAll();
	}
	
	@GetMapping("/all")
	public List<AccountHolder> getAll() {
		return accrepo.findAll();
	}
	
	
	
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
	public @ResponseBody ArrayList<CheckingAccount> addCheckingAccountsToId(@PathVariable int id , @RequestBody @Valid CheckingAccount c) throws NoSuchResorceFoundException, ExceedsCombinedBalanceLimitException{
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		a.addCheckingAccounts(c); 
		return a.getCheckingAccounts();
	}  
			
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public @ResponseBody ArrayList<CheckingAccount> getCheckingAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		return a.getCheckingAccounts();
	}
	
	
	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ArrayList<SavingsAccount> addSavingsAccountsToId(@RequestBody @Valid SavingsAccount c , @PathVariable int id) throws NoSuchResorceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		a.addSavingsAccounts(c); 
		return a.getSavingsAccounts();
	}
	
			
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public @ResponseBody ArrayList<SavingsAccount> getSavingsAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		return a.getSavingsAccounts();
	}
	
	
	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody ArrayList<CDAccount> addCDAccountsToId(@RequestBody @Valid CDAccount c , @PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		a.addCDAccounts(c); 
		return a.getCdAccounts();
	}
	
			
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	public @ResponseBody ArrayList<CDAccount> getCDAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = MeritBank.GetAccountHolderById(id);
		return a.getCdAccounts();
	}
	
		
	@PostMapping(value = "/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering addCDOffering(@RequestBody @Valid CDOffering c) {
		MeritBank.addCDOffering(c);
		return c;
	}
	
	
	@GetMapping(value = "/CDOfferings")
	public ArrayList<CDOffering> getCDOfferings() {
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
