package com.bank.eventhandler.service;

public class ResponseBody {

	private int status;
	private String message;
	
	ResponseBody(int status,String message){
		this.status=status;
		this.message=message;
	}
	
	ResponseBody(){}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
