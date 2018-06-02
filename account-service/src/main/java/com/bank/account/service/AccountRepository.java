/**
 * 
 */
package com.bank.account.service;

import java.util.List;

/**
 *
 */
public interface AccountRepository {
	
	List<Account> getAllAccounts();
	
	Account getAccount(String number);
	
	String create(String custId, String amount);
	
	String create(String custId, String amount,String number);
	
	String updatewithCustId(String number,String custId);
}
