/**
 * 
 */
package com.bank.product.service;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 */
public interface ProductRepository {

	List<Product> getAllProducts();

	Product getProductById(String id);

	Product getProductByName(String name);

	Map<String, Product> create(List<ProductDTO> products);

	int delete(String id);

	int patchUpdate(String id, String name);

}
