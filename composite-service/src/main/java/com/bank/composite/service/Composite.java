/**
 * 
 */
package com.bank.composite.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * 
 *
 */
public class Composite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String traceId = UUID.randomUUID().toString();

	private UserDTO userDTO;
	private AccountDTO accountDTO;
	
	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
		this.userDTO.setTraceId(traceId);
	}

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
		this.accountDTO.setTraceId(traceId);
		this.accountDTO.setNumber(traceId);
	}

	@Override
	public String toString() {
		return "Composite [userDTO=" + this.userDTO + ", accountDTO=" + accountDTO +"]";
	}

	public Composite(UserDTO userDTO, AccountDTO accountDTO) {
		super();
		this.userDTO = userDTO;
		this.accountDTO = accountDTO;
	}
	public Composite() {}

}
