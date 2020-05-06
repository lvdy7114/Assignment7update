package com.meritamerica.assignment5.models;

import org.hibernate.validator.constraints.NotBlank;


public class AccountHolder {
	
	public static int nextid = 0;
	
	
	private int id;
	
	@NotBlank(message = "firtName is missing")
	private String firstName;
		
	private String middleName;
	
	@NotBlank(message = "lastName is missing")
	private String lastName;
	
	@NotBlank(message = "ssn is missing")
	private String ssn;
	private BankAccount[] bankAccounts;
	
	
	
	public AccountHolder() {
		this.id = ++nextid;
		this.firstName = "";
		this.lastName = "";
		this.ssn = "";
	}
	
	//need to adjust this to suit the calls when I find out what they are.
	public AccountHolder(String firstName, String middleName, String lastName, String ssn){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		bankAccounts = null;
		this.id = ++nextid;
	}
	
	public AccountHolder(String firstName, String middleName, String lastName, String ssn, double checkingAccountOpeningBalance, double savingsAccountOpeningBalance) {
		
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		CheckingAccount CA1 = new CheckingAccount(checkingAccountOpeningBalance);
		SavingsAccount SA1 = new SavingsAccount(savingsAccountOpeningBalance);
		BankAccount[] ba = {CA1 , SA1};
		setBankAccounts(ba);
		this.id = ++nextid;
		
	}


	public AccountHolder (String firstName, String middleName, String lastName, String ssn , BankAccount starterBankAccount){
		
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		BankAccount[] ba = {starterBankAccount};
		setBankAccounts(ba);
		this.id = ++nextid;
	}
	
    public AccountHolder (String firstName, String middleName, String lastName, String ssn , BankAccount starterBankAccount , BankAccount seccountBankAccount){
		
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		BankAccount[] ba = {starterBankAccount , seccountBankAccount};
		setBankAccounts(ba);
		this.id = ++nextid;
    }
	
	
	
	public double getCombinedBalance() {
		double holdersTotal = 0;
			if(bankAccounts != null){
				for(int i = 0 ; i < bankAccounts.length ; i++){
					holdersTotal += bankAccounts[i].getBalance();
				}
			}
		return holdersTotal;
	}
	
    
    
    
	public void setBankAccounts(BankAccount[] BA){
		if(bankAccounts == null){
			bankAccounts = BA;			
		}else{
			BankAccount[] temp = new BankAccount[bankAccounts.length + BA.length];
			for(int i = 0 ; i < bankAccounts.length ; i++){
				temp[i] = bankAccounts[i];
			}
			for(int i = bankAccounts.length ; i < bankAccounts.length + BA.length ; i++){
				temp[i] = BA[i - bankAccounts.length];
			}
			bankAccounts = temp;
		}
		
		
	}
	
	public CheckingAccount addCheckingAccount(double startBalance) throws ExceedsCombinedBalanceLimitException {
		if(getCombinedBalance() + startBalance >= ExceedsCombinedBalanceLimitException.getCombinedbalancelimit()){
			throw new ExceedsCombinedBalanceLimitException();
		}
		CheckingAccount toBeAdded = new CheckingAccount(startBalance);
		BankAccount[] temp = {toBeAdded};
		setBankAccounts(temp);
		
		//adds deposit to transaction list
		DepositTransaction dt = new DepositTransaction(toBeAdded , startBalance);
		toBeAdded.addTransaction(dt);
		
		if(startBalance > FraudQueue.getExcessiveAmount()){
			MeritBank.addToFraudQueue(dt);
			
		}
			
		return toBeAdded;
		
	}
	
	public CheckingAccount addCheckingAccount(CheckingAccount CheckingAccount) throws ExceedsCombinedBalanceLimitException {
		Double balance = CheckingAccount.getBalance();
		
		if(getCombinedBalance() + balance >= ExceedsCombinedBalanceLimitException.getCombinedbalancelimit()){
			throw new ExceedsCombinedBalanceLimitException();
		}
		
		BankAccount[] temp = {CheckingAccount};
		setBankAccounts(temp);
		
		//adds deposit to transaction list
		DepositTransaction dt = new DepositTransaction(CheckingAccount , balance);
		CheckingAccount.addTransaction(dt);
		
		if(balance > FraudQueue.getExcessiveAmount()){
			MeritBank.addToFraudQueue(dt);
			
		}		
		
		return CheckingAccount;
		
	}
	
	public SavingsAccount addSavingsAccount(double startBalance) throws ExceedsCombinedBalanceLimitException {
		if(getCombinedBalance() + startBalance >= ExceedsCombinedBalanceLimitException.getCombinedbalancelimit()){
			throw new ExceedsCombinedBalanceLimitException();
		}
		SavingsAccount toBeAdded = new SavingsAccount(startBalance);
		BankAccount[] temp = {toBeAdded};
		setBankAccounts(temp);
		
		//adds deposit to transaction list
		DepositTransaction dt = new DepositTransaction(toBeAdded , startBalance);
		toBeAdded.addTransaction(dt);
		
		if(startBalance > FraudQueue.getExcessiveAmount()){
			MeritBank.addToFraudQueue(dt);
			
		}		
		
		return toBeAdded;
	}
	
	public SavingsAccount addSavingsAccount(SavingsAccount SavingsAccount) throws ExceedsCombinedBalanceLimitException {
		Double balance = SavingsAccount.getBalance();
		
		if(getCombinedBalance() + balance >= ExceedsCombinedBalanceLimitException.getCombinedbalancelimit()){
			throw new ExceedsCombinedBalanceLimitException();
		}
		
		BankAccount[] temp = {SavingsAccount};
		setBankAccounts(temp);
		
		//adds deposit to transaction list
		DepositTransaction dt = new DepositTransaction(SavingsAccount , balance);
		SavingsAccount.addTransaction(dt);
		
		if(balance > FraudQueue.getExcessiveAmount()){
			MeritBank.addToFraudQueue(dt);
			
		}		
		
		return SavingsAccount;
		
	}	
	
	public CDAccount addCDAccount(CDOffering cDOffering , double startBalance) throws ExceedsFraudSuspicionLimitException , NegativeAmountException {
		
		CDAccount toBeAdded = new CDAccount(cDOffering , startBalance);
		BankAccount[] temp = {toBeAdded};
		setBankAccounts(temp);
		
		//adds deposit to transaction list
		Double balance = toBeAdded.getBalance();
		DepositTransaction dt = new DepositTransaction(toBeAdded , balance);
		toBeAdded.addTransaction(dt);
		
		if(balance > FraudQueue.getExcessiveAmount()){
			MeritBank.addToFraudQueue(dt);
			throw new ExceedsFraudSuspicionLimitException();
		}	
		
		
		return toBeAdded;				
				
	}
	
	public CDAccount addCDAccount(CDAccount CDAccount) {
		
		BankAccount[] temp = {CDAccount};
		setBankAccounts(temp);
		
		//adds deposit to transaction list
		Double balance = CDAccount.getBalance();
		DepositTransaction dt = new DepositTransaction(CDAccount , balance);
		CDAccount.addTransaction(dt);		
		
		if(balance > FraudQueue.getExcessiveAmount()){
			MeritBank.addToFraudQueue(dt);
			
		}	
		
		return CDAccount;				
				
	}
	
	
	public BankAccount[] getBankAccounts() {
		return bankAccounts;
	}
	
	
		
	public static int getNextid() {
		return nextid;
	}

	public static void setNextid(int nextid) {
		AccountHolder.nextid = nextid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;}
	
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
		
	}
	
	public String getLastName() {
		return lastName;
	}
	
	
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}

	public String getSSN(){
		return ssn;
	}
	

	
	public int getNumberOfCheckingAccounts() {
		
		int counterCA = 0;
		if(bankAccounts != null){
			for(BankAccount ba : bankAccounts) {
						
				Class<? extends BankAccount> c = ba.getClass();
				if(c == CDAccount.class) {
					counterCA++;
				}
			}
	
		}
		return counterCA;
	}
	
	public CheckingAccount[] getTheCheckingAccounts(AccountHolder a) {
		CheckingAccount[] c = new CheckingAccount[a.getNumberOfCheckingAccounts()];
		int counter = 0;
			if(a.bankAccounts != null){
				for(BankAccount ba : a.bankAccounts) {
							
					Class<? extends BankAccount> d = ba.getClass();
					if(d == CDAccount.class) {
					c[counter] = (CheckingAccount) ba;
					counter++;
					}
				}
		
			}
		
		return c;
	}

	
	public int getNumberOfSavingsAccounts() {
		
		int counterSA = 0;
		if(bankAccounts != null){
			for(BankAccount ba : bankAccounts) {
						
				Class<? extends BankAccount> c = ba.getClass();
				if(c == CDAccount.class) {
					counterSA++;
				}
			}
	
		}
		return counterSA;
	}
	

	

	public SavingsAccount[] getTheSavingsAccounts(AccountHolder a) {
		SavingsAccount[] c = new SavingsAccount[a.getNumberOfSavingsAccounts()];
		int counter = 0;
			if(a.bankAccounts != null){
				for(BankAccount ba : a.bankAccounts) {
							
					Class<? extends BankAccount> d = ba.getClass();
					if(d == CDAccount.class) {
					c[counter] = (SavingsAccount) ba;
					counter++;
					}
				}
		
			}
		
		return c;
	}

	public int getNumberOfCDAccounts() {
		
		int counterCDA = 0;
		if(bankAccounts != null){
			for(BankAccount ba : bankAccounts) {
						
				Class<? extends BankAccount> c = ba.getClass();
				if(c == CDAccount.class) {
					counterCDA++;
				}
			}
	
		}
		return counterCDA;
		
	}
	

	

	public CDAccount[] getTheCDAccounts(AccountHolder a) {
		CDAccount[] c = new CDAccount[a.getNumberOfCDAccounts()];
		int counter = 0;
			if(a.bankAccounts != null){
				for(BankAccount ba : a.bankAccounts) {
							
					Class<? extends BankAccount> d = ba.getClass();
					if(d == CDAccount.class) {
					c[counter] = (CDAccount) ba;
					counter++;
					}
				}
		
			}
		
		return c;
	}	
	
	public static AccountHolder[] sortAccounts(AccountHolder[] toBeSorted) {
		AccountHolder tempAH; 
		
		for(int i = 0 ; i < toBeSorted.length ; i++) {
			for(int j = i + 1 ; j < toBeSorted.length ; j++) {
				if(toBeSorted[i].getCombinedBalance() > toBeSorted[j].getCombinedBalance()) {
					tempAH = toBeSorted[i];
					toBeSorted[i] = toBeSorted[j];
					toBeSorted[j] = tempAH;
					
				}
			}
		}
		return toBeSorted;
		
	}
	
	public String writeToString() {
		StringBuilder holderSB = new StringBuilder(firstName + "," + middleName + "," + lastName + "," + ssn + "\n");
		
		int counterCA = getNumberOfCheckingAccounts();
		int counterSA = getNumberOfSavingsAccounts();
		int counterCDA = getNumberOfCDAccounts();
	
		holderSB.append(counterCA + "\n");		
		
		for(BankAccount ba : bankAccounts) {
			
			Class<? extends BankAccount> c = ba.getClass();
			if(c == CheckingAccount.class) {
				holderSB.append(ba.writeToString() + "\n");				
			}					
		}
		holderSB.append(counterSA + "\n");
		
        for(BankAccount ba : bankAccounts) {
			
			Class<? extends BankAccount> c = ba.getClass();
			if(c == SavingsAccount.class) {
				holderSB.append(ba.writeToString() + "\n");				
			}
        }
        holderSB.append(counterCDA + "\n");
		
        for(BankAccount ba : bankAccounts) {
			
			Class<? extends BankAccount> c = ba.getClass();
			if(c == CDAccount.class) {
				holderSB.append(ba.writeToString() + "\n");				
			}
        }
                	
		String toBeReturned = holderSB.toString();
		return toBeReturned;
	}
	
	static AccountHolder readFromString(String accountHolderData) {
		AccountHolder toBeAdded = null;
		try{
			
			String[] toBeParsed = accountHolderData.split(",");
			String firstNameToBeAdded = toBeParsed[0];
			String middleNameToBeAdded = toBeParsed[1];
			String lastNameToBeAdded = toBeParsed[2];
			String ssnToBeAdded = toBeParsed[3];
			
			toBeAdded = new AccountHolder(firstNameToBeAdded , middleNameToBeAdded , lastNameToBeAdded , ssnToBeAdded);
			
		}catch(java.lang.Exception exception){
			
		}
		return toBeAdded;
		
	}
	
			
			//throws Exception	
			//Should throw a java.lang.Exception if String cannot be correctly parsed

	


    
	
}
