/**
 * 
 */
package com.bank.product.service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 *
 */
@RestController
public class ProductController {

	protected Logger logger = Logger.getLogger(ProductController.class.getName());

	@Autowired
	@Qualifier("productDBRepoImpl")
	ProductRepository productRepository;
	
	@Autowired
	MessageSender messageSender;
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public Product[] all() {
		logger.info("products-microservice all() invoked");
		List<Product> products = productRepository.getAllProducts();
		logger.info("products-microservice all() found: " + products.size());
		return products.toArray(new Product[products.size()]);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public Product byId(@PathVariable("id") String id) {
		logger.info("products-microservice byId() invoked: " + id);
		Product product = productRepository.getProductById(id);
		logger.info("products-microservice byId() found: " + product);
		return product;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> create(@RequestBody List<ProductDTO> products) {
		logger.info("products-microservice create invoked: " + products);
		Map<String,Product> createdProducts = productRepository.create(products);
		
		logger.info("products-microservice created: " + (createdProducts == null  ? 0 : createdProducts.size()));

		if (createdProducts == null)
			return ResponseEntity.noContent().build();
		ProductDTO[] arrProduct= new ProductDTO[products.size()];
		Message<ProductDTO[]> message = new Message<ProductDTO[]>("ProductCreatedEvent", products.toArray(arrProduct));
		messageSender.send(message);

		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(createdProducts);
	}
}
