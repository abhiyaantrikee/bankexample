package com.bank.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * 
 *
 */
@Repository
@Qualifier("stubUserRepository")
public class StubUserRepository implements UserRepository {

	private Map<String, User> usersByEmail = new HashMap<String, User>();

	public StubUserRepository() {
		User user = new User("Shayam", "Bisht", "Kumar", "1-Jan-1978", "shayam.kumar.bisht@gmail.com", "+919999999999");
		usersByEmail.put(user.getEmail(), user);
		user = new User("Ram", "", "Kumar", "1-Jun-1989", "ram.kumar@gmail.com", "+918888888888");
		usersByEmail.put(user.getEmail(), user);
		user = new User("Sidd", "Kapoor", "", "1-Apr-1985", "sidd.kapoor@gmail.com", "+917777777777");
		usersByEmail.put(user.getEmail(), user);
		Logger.getLogger(StubUserRepository.class).info("Created StubUserRepository");
	}

	@Override
	public List<User> getAllUsers() { 
		return new ArrayList<User>(usersByEmail.values());
	}

	@Override
	public User getUserByEmail(String email) {
		return usersByEmail.get(email);
	}
	
	@Override
	public User getUserById(String id) {
		return usersByEmail.get(id);
	}

	@Override
	public String create(String firstName, String lastName, String middleName, String dob, String email,
			String phoneNumber) {
		User user = new User(firstName, lastName, middleName, dob, email, phoneNumber);
		usersByEmail.put(user.getEmail(), user);
		return user.getEmail();
	}

}
