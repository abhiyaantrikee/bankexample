package com.bank.composite.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
@Qualifier("stubAccountRepository")
public class StubCompositeRepository implements CompositeRepository {
	
	private Map<String, Composite> accountsByNumber = new HashMap<String, Composite>();
	
	public StubCompositeRepository() {
		Logger.getLogger(StubCompositeRepository.class).info("Initialized StubCompositeRepository successfully !!! ");
	}

	@Override
	public String create(Composite composite) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
