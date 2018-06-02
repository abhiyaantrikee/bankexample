/**
 * 
 */
package com.bank.composite.service;

import java.net.URI;
import java.util.logging.Logger;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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
public class CompositeController {

	protected Logger logger = Logger
			.getLogger(CompositeController.class.getName());
	
	@Autowired
	@Qualifier("compositeDBRepoImpl")
	CompositeRepository compositeRepository;
	
	
	@RequestMapping(value = "/composite", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody Composite composite) {
		ResponseBody response= new ResponseBody();
		
		logger.info("composite-service create() invoked");
		String id = compositeRepository.create(composite);
		
		if (id == null) {
			response.setMessage("Opps Something Wrong!!!");
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			return ResponseEntity.created(null).body(response);
		}
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		response.setMessage("Request has been Processed");
		response.setStatus(HttpStatus.SC_CREATED);	
		logger.info("composite-service created  with location: " + location);
		return ResponseEntity.created(location).body(response);
	}
	
	
}
