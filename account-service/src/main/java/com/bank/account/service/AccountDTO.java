/**
 * 
 */
package com.bank.account.service;

import java.io.Serializable;

/**
 * 
 *
 */
public class AccountDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String traceId;
	private String amount;
	private String number;
	private String custId;

	public String getCustId() {
		return custId;
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

	public AccountDTO(String amount, String custId, String number) {
		super();
		this.amount = amount;
		this.number = number;
		this.custId = custId;
	}

	public AccountDTO() {
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

}
