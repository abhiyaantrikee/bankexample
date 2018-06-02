/**
 * 
 */
package com.bank.account.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * 
 *
 */
@Document(collection = "account")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String amount;
	@Indexed
	private String number;
	private String custId;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdTimestamp;

	public String getCustId() {
		return custId;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Account [amount=" + amount + ", number=" + number + ", id=" + custId + "]";
	}

	public Account(String amount, String custId, String number) {
		super();
		this.amount = amount;
		this.number = number;
		this.custId = custId;
		this.createdTimestamp = Calendar.getInstance().getTime();
	}

}
