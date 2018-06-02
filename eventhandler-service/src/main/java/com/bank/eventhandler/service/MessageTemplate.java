package com.bank.eventhandler.service;

import java.util.Date;

public interface MessageTemplate<T> {

	public String getMessageType();

	T getPayload();

	Date getTimestamp();

	String getId();
}
