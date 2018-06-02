/**
 * 
 */
package com.bank.user.service;

import java.util.List;

/**
 * 
 *
 */
public interface UserRepository {

	List<User> getAllUsers();

	User getUserByEmail(String email);

	User getUserById(String id);

	String create(String firstName, String lastName, String middleName, String dob, String email, String phoneNumber);
}
