package com.bank.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;

@Repository 
@Qualifier("userDBRepoImpl")
public class UserDBRepoImpl implements UserRepository {

	@Autowired
	private MongoOperations operations;

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return operations.findAll(User.class);
	}

	@Override
	public User getUserByEmail(String email) {
		// query to search account
		Query searchQuery = new Query(Criteria.where("email").is(email));
		return operations.findOne(searchQuery,User.class);
	}
	
	@Override
	public User getUserById(String id) {
		// query to search account
		Query searchQuery = new Query(Criteria.where("id").is(id));
		return operations.findOne(searchQuery,User.class);
	}

	@Override
	public String create(String firstName, String lastName, String middleName, String dob, String email, String phoneNumber) {
		// TODO Auto-generated method stub
		User user = new User(firstName, lastName, middleName, dob, email, phoneNumber);
		operations.save(user);
		User newUser = getUserByEmail(email);
		return newUser.getId();
	}

}
