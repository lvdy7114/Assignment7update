package com.meritamerica.assignment5.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.Exceptions.NoSuchResorceFoundException;
import com.meritamerica.assignment5.models.AccountHolder;
import com.meritamerica.assignment5.models.AccountHoldersContactDetails;
import com.meritamerica.assignment5.models.AuthenticationRequest;
import com.meritamerica.assignment5.models.AuthenticationResponse;
import com.meritamerica.assignment5.models.CDAccount;
import com.meritamerica.assignment5.models.CDOffering;
import com.meritamerica.assignment5.models.CheckingAccount;
import com.meritamerica.assignment5.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment5.models.MeritBank;
import com.meritamerica.assignment5.models.NegativeAmountException;
import com.meritamerica.assignment5.models.SavingsAccount;
import com.meritamerica.assignment5.models.Users;
import com.meritamerica.assignment5.repositories.AccountHolderRepository;
import com.meritamerica.assignment5.repositories.AccountHoldersContactDetailsRepository;
import com.meritamerica.assignment5.repositories.CDAccountRepository;
import com.meritamerica.assignment5.repositories.CDOfferingRepository;
import com.meritamerica.assignment5.repositories.CheckingAccountRepository;
import com.meritamerica.assignment5.repositories.SavingsAccountRepository;
import com.meritamerica.assignment5.repositories.UsersRepository;
import com.meritamerica.assignment5.util.JwtUtil;
import com.meritamerica.assignment5.util.MyUserDetailsService;

//@RequestMapping("/request")
@RestController
public class AccountHolderController {
	
	@Autowired
	private AccountHolderRepository accountHolderRepository;
	
	@Autowired
	private AccountHoldersContactDetailsRepository accountHoldersContactDetailsRepository;
	
	@Autowired
	private CheckingAccountRepository checkingAccountRepository;
	
	@Autowired
	private SavingsAccountRepository savingsAccountRepository;
	
	@Autowired
	private CDAccountRepository cdAccountRepository;
	
	@Autowired
	private CDOfferingRepository cdofferingRepository;
	
	 @Autowired
	private UsersRepository usersRepository;
	 
	 @Autowired 
	 private AuthenticationManager authenticationManager;
	 
	 @Autowired
	 private MyUserDetailsService userDetailsService;
	 
	 @Autowired
	 private JwtUtil jwtTokenUtil;
	 
	 
	@RequestMapping(value = "/hello")
	public String test() {
		//System.out.println("Hello!");
		return "hello world";
	}
	
	@PostMapping(value = "/AccountHolders")
  	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder postAccounHolder(@RequestBody @Valid AccountHolder accountHolder) {				
  		/*accountHolderRepository.save(a);
  			return a;	
  		*/
  			Users users = usersRepository.getOne((int) accountHolder.getUsers().getId().longValue());
  			users.setAccountHolder(accountHolder);
  			accountHolder.setUser(users);;
  			accountHolderRepository.save(accountHolder);
  			return accountHolder; 
  	}
	
	@GetMapping("/AccountHolders")
	public List<AccountHolder> getAll() {
		return accountHolderRepository.findAll();
	}
	
	@GetMapping(value = "/AccountHolders/{id}")
	public @ResponseBody AccountHolder getAccountHolderBytheId(@PathVariable int id) throws NoSuchResorceFoundException {
		return accountHolderRepository.findOne(id);  
		
	}
	
	@PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody CheckingAccount addCheckingAccountsToId(@PathVariable int id , @RequestBody @Valid CheckingAccount c) throws NoSuchResorceFoundException, ExceedsCombinedBalanceLimitException{
		AccountHolder a = accountHolderRepository.findOne(id); 		 
		a.addCheckingAccount(c);
		checkingAccountRepository.save(c);
		return c;
	}  
	
		
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public @ResponseBody List<CheckingAccount> getCheckingAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = accountHolderRepository.findOne(id);
		return a.getCheckingAccounts();
	}
	
	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody SavingsAccount addSavingsAccountsToId(@RequestBody @Valid SavingsAccount c , @PathVariable int id) throws NoSuchResorceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder a = accountHolderRepository.findOne(id);
		a.addSavingsAccount(c);
		savingsAccountRepository.save(c);
		return c;
	}
	
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public @ResponseBody List<SavingsAccount> getSavingsAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = accountHolderRepository.findOne(id);
		return a.getSavingsAccounts();
	}
	
	
	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody CDAccount addCDAccountsToId(@RequestBody @Valid CDAccount c , @PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = accountHolderRepository.findOne(id);
		a.addCDAccount(c);
		cdAccountRepository.save(c);
		return c;
	}
	
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	public @ResponseBody List<CDAccount> getCDAccounts(@PathVariable int id) throws NoSuchResorceFoundException {
		AccountHolder a = accountHolderRepository.findOne(id);
		return a.getCdAccounts();
	}
	
	
	
//this part no more in assignment 7
	@PostMapping(value="/AccountHolders/{id}/ContactDetails")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHoldersContactDetails postContactDetails( @PathVariable int id ,@RequestBody @Valid AccountHoldersContactDetails contactDetails) {				
		AccountHolder a = accountHolderRepository.findOne(id);
		a.setAccountHoldersContactDetails(contactDetails);
		accountHoldersContactDetailsRepository.save(contactDetails);
		
		return contactDetails;	
  	}

	@GetMapping("/AccountHolders/{id}/ContactDetails")
	public @ResponseBody AccountHoldersContactDetails getContactDetails(@PathVariable int id ) {
		AccountHolder a = accountHolderRepository.findOne(id);
		AccountHoldersContactDetails deets = a.getAccountHoldersContactDetails();
		 return deets;
	 }
	

	
	
	@PostMapping(value = "/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering addCDOffering(@RequestBody @Valid CDOffering c) {
		cdofferingRepository.save(c);
		return c;
	}
	
	
	@GetMapping(value = "/CDOfferings")
	public List<CDOffering> getCDOfferings() {
		
		return cdofferingRepository.findAll();
	}

	

	//Assignment 7 starts here
	
	
	@RequestMapping(value= "/authenticate", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		System.out.println("in create authentication token");
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
				);
		}catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
	
	final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
	
	final String jwt = jwtTokenUtil.generateToken(userDetails);
			//generateToken(userDetails);
	
	return ResponseEntity.ok(new AuthenticationResponse(jwt));
	
	
	}

	@PostMapping(value = "/authenticate/CreateUser")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createUser(@RequestBody Users users){
		usersRepository.save(users);
		return ResponseEntity.ok(users);
	}
	
	@GetMapping(value= "/Me")
	public AccountHolder getMe(@RequestHeader (name="Authorization") String token) {
		token = token.substring(7);
		Users users = usersRepository.findByUsername(jwtTokenUtil.extractUsername(token)).get();
		Integer i = users.getAccountHolder().getId();
		return accountHolderRepository.findById(i.intValue());

	}
	
	@PostMapping(value = "/Me/CheckingAccounts")
	public CheckingAccount addMeChecking(@RequestHeader (name = "Authorization")String token, @RequestBody CheckingAccount checking) throws ExceedsCombinedBalanceLimitException, 
										NegativeAmountException {
		token = token.substring(7);
		Users users = usersRepository.findByUsername(jwtTokenUtil.extractUsername(token)).get();
		AccountHolder account = accountHolderRepository.findById(users.getAccountHolder().getId()); 
		account.addCheckingAccount(checking);
		checkingAccountRepository.save(checking); 
		return checking; 
	}
	
	@GetMapping(value = "/Me/CheckingAccounts")
	public List<CheckingAccount> getMeChecking(@RequestHeader (name = "Authorization")String token) {
		token = token.substring(7);
		Users users = usersRepository.findByUsername(jwtTokenUtil.extractUsername(token)).get();
		return accountHolderRepository.findById(users.getAccountHolder().getId().intValue()).getCheckingAccounts();
	}
	
	@PostMapping(value = "/Me/SavingsAccount")
	public SavingsAccount addMeSavings(@RequestHeader (name = "Authorization")String token, @RequestBody SavingsAccount savings)
								throws ExceedsCombinedBalanceLimitException, NegativeAmountException {
		token = token.substring(7);
		Users users = usersRepository.findByUsername(jwtTokenUtil.extractUsername(token)).get();
		AccountHolder account = accountHolderRepository.findById(users.getAccountHolder().getId());
		account.addSavingsAccount(savings);
		accountHolderRepository.save(account);
		return savings;
	}

	@GetMapping(value = "/Me/SavingsAccount")
	public List<SavingsAccount> getMeSavings(@RequestHeader (name = "Authorization")String token) {
		token = token.substring(7);
		Users users = usersRepository.findByUsername(jwtTokenUtil.extractUsername(token)).get();
		return accountHolderRepository.findById(users.getAccountHolder().getId().intValue()).getSavingsAccounts();
	}
	
	@PostMapping(value = "/Me/CDAccount")
	public CDAccount addMeCDAccount(@RequestHeader (name = "Authorization")String token, @RequestBody CDAccount cdAccount) throws ExceedsCombinedBalanceLimitException, NegativeAmountException {
		token = token.substring(7);
		Users users = usersRepository.findByUsername(jwtTokenUtil.extractUsername(token)).get();
		AccountHolder account = accountHolderRepository.findById(users.getAccountHolder().getId());
		account.addCDAccount(cdAccount);
		accountHolderRepository.save(account);
		return cdAccount;
	}
	
	@GetMapping(value = "/Me/CDAccount")
	public List<CDAccount> getMeCDAccount(@RequestHeader (name = "Authorization")String token) {
		token = token.substring(7);
		Users users = usersRepository.findByUsername(jwtTokenUtil.extractUsername(token)).get();
		return accountHolderRepository.findById(users.getAccountHolder().getId().intValue()).getCdAccounts();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
