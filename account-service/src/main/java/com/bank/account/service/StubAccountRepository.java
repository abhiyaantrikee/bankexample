package com.bank.account.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
@Qualifier("stubAccountRepository")
public class StubAccountRepository implements AccountRepository {
	
	private Map<String, Account> accountsByNumber = new HashMap<String, Account>();
	
	public StubAccountRepository() {
		Logger.getLogger(StubAccountRepository.class).info("Initialized StubAccountRepository successfully !!! ");
	}
	
	public List<Account> getAllAccounts() {
		return new ArrayList<Account>(accountsByNumber.values());
	}

	public Account getAccount(String number) {
		return accountsByNumber.get(number);
	}
	
	@Override
	public String create(String custId, String amount) {
		
		Account account = new Account(amount, custId , UUID.randomUUID().toString());
		accountsByNumber.put(account.getNumber(), account);
		Logger.getLogger(StubAccountRepository.class).info("Created StubAccountRepository");
		
		return account.getNumber();
	}

	@Override
	public String create(String custId, String amount, String number) {
		// TODO Auto-generated method stub

		
		Account account = new Account(amount, custId , number);
		accountsByNumber.put(account.getNumber(), account);
		Logger.getLogger(StubAccountRepository.class).info("Created StubAccountRepository");
		
		return account.getNumber();
	
	}

	@Override
	public String updatewithCustId(String number, String custId) {
		// TODO Auto-generated method stub
		return null;
	}

}
