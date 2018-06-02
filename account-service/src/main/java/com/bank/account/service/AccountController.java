/**
 * 
 */
package com.bank.account.service;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * 
 *
 */
@RestController
public class AccountController {

	protected Logger logger = Logger
			.getLogger(AccountController.class.getName());
	
	@Autowired
	@Qualifier("accountDBRepoImpl")
	AccountRepository accountRepository;
	
	@RequestMapping(value="/accounts",method=RequestMethod.GET)
	public Account[] all() {
		logger.info("accounts-service all() invoked");
		List<Account> accounts = accountRepository.getAllAccounts();
		logger.info("accounts-service all() found: " + accounts.size());
		return accounts.toArray(new Account[accounts.size()]);
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody AccountDTO acct) {
		ResponseBody response= new ResponseBody();
		logger.info("accounts-service create() invoked");
		String id = accountRepository.create(acct.getCustId(), acct.getAmount(), acct.getTraceId());
		
		if (id == null){
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			response.setMessage("OOPs Something Wrong!!");
			return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).body(response);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		logger.info("accounts-service created account with location: " + location);
		response.setMessage("Account Has Been Created");
		response.setStatus(HttpStatus.SC_CREATED);
		// return the response to the HTTP Request
		return ResponseEntity.created(location).body(response);
	}
	
	
	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Object> generateAccount(@PathVariable("id") String id, @RequestBody String custId) {
		//Account account=accountRepository.getAccount(id);
		ResponseBody response= new ResponseBody();
		String newid=accountRepository.updatewithCustId(id, custId);
		if (newid == null){
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			response.setMessage("OOPs Something Wrong!!");
			return  ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).body(response);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newid).toUri();
		logger.info("accounts-service cgenerated account with location: " + location);
		response.setMessage("Account Has Been Created");
		response.setStatus(HttpStatus.SC_CREATED);
		// return the response to the HTTP Request
		return ResponseEntity.created(location).body(response);
	}
	
	
	@RequestMapping("/accounts/{id}")
	public Account byId(@PathVariable("id") String id) {
		logger.info("accounts-service byId() invoked: " + id);
		Account account = accountRepository.getAccount(id);
		logger.info("accounts-service byId() found: " + account);
		return account;
	}
	
	
}
