package com.bank.account.service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;

@Repository
@Qualifier("accountDBRepoImpl")
public class AccountDBRepoImpl implements AccountRepository {

	@Autowired
	private MongoOperations operations;
	
	protected Logger logger = Logger.getLogger(AccountDBRepoImpl.class.getName());

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return operations.findAll(Account.class);
	}

	@Override
	public Account getAccount(String number) {
		// query to search account
		Query searchQuery = new Query(Criteria.where("number").is(number));
		return operations.findOne(searchQuery, Account.class);
		
	}

	@Override
	public String create(String custId, String amount) {
		// TODO Auto-generated method stub
		String number = UUID.randomUUID().toString();
		Account account = new Account(amount, custId, number);
		operations.save(account);
		Account newAcct = getAccount(number);
		return newAcct.getNumber();
	}
	
	@Override
	public String create(String custId, String amount,String number) {
		// TODO Auto-generated method stub
		// number = UUID.randomUUID().toString();
		Account account = new Account(amount, custId, number);
		operations.save(account);
		Account newAcct = getAccount(number);
		return newAcct.getNumber();
	}

	public String updatewithCustId(String number,String custId) {
		Query searchquery=new Query(Criteria.where("number").is(number));
		String accountnumber=(int)(Math.random()*10000)+"";
		Update update=new Update();
		update.set("number", accountnumber);
		update.set("custId", custId);
		WriteResult wr =operations.updateFirst(searchquery, update, Account.class);
		if(true!=wr.wasAcknowledged()) {
			accountnumber="";
		}
		return accountnumber;
		
	}
}
