/**
 * 
 */
package com.bank.user.service;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
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
public class UserController {

	protected Logger logger = Logger.getLogger(UserController.class.getName());

	@Autowired
	@Qualifier("userDBRepoImpl")
	UserRepository userRepository;

	@Autowired
	MessageSender messageSender;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public User[] all() {
		logger.info("users-microservice all() invoked");
		List<User> users = userRepository.getAllUsers();
		logger.info("users-microservice all() found: " + users.size());
		return users.toArray(new User[users.size()]);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User byId(@PathVariable("id") String id) {
		logger.info("users-microservice byId() invoked: " + id);
		User user = userRepository.getUserById(id);
		logger.info("users-microservice byId() found: " + user);
		return user;
	}

	@RequestMapping(value = "/users/email/{email}", method = RequestMethod.GET)
	public User byEmail(@PathVariable("email") String email) {
		logger.info("users-microservice byId() invoked: " + email);
		User user = userRepository.getUserByEmail(email);
		logger.info("users-microservice byId() found: " + user);
		return user;
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody UserDTO user) {
		
		ResponseBody response= new ResponseBody();
		
		logger.info("users-microservice create invoked: " + user.getEmail());
		String id = userRepository.create(user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getDob(),
				user.getEmail(), user.getPhoneNumber());
		logger.info("users-microservice created with id: " + id);

		if (id == null) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			response.setMessage("OOPs Something Wrong!!");
			return ResponseEntity.created(null).body(response);
		}
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		// send a message to Kafka that USer has been created.

		user.setId(id);
		Message<UserDTO> message = new Message<UserDTO>("UserCreatedEvent", user);
		message.setTraceId(user.getTraceId());
		messageSender.send(message);
		
		response.setMessage("User Has Been Created");
		response.setStatus(HttpStatus.SC_CREATED);
		// return the response to the HTTP Request
		return ResponseEntity.created(location).body(response);
	}
}
