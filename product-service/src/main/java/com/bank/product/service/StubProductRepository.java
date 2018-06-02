package com.bank.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * 
 *
 */
@Repository
@Qualifier("stubUserRepository")
public class StubProductRepository implements ProductRepository {

	private Map<String, Product> usersByEmail = new HashMap<String, Product>();

	public StubProductRepository() {
		Product product = new Product("Checking Account");
		usersByEmail.put(UUID.randomUUID().toString(), product);
		product = new Product("Savings Account");
		usersByEmail.put(UUID.randomUUID().toString(), product);
		product = new Product("Credit Card");
		usersByEmail.put(UUID.randomUUID().toString(), product);
		Logger.getLogger(StubProductRepository.class).info("Created StubProductRepository");
	}

	@Override
	public Map<String, Product> create(List<ProductDTO> products) {

		for (ProductDTO product : products) {
			Product productName = new Product(product.getName());
			String uuid = UUID.randomUUID().toString();
			usersByEmail.put(uuid, productName);
		}
		return usersByEmail;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int patchUpdate(String id, String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product getProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
