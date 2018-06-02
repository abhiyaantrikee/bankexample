package com.bank.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;

@Repository
@Qualifier("userDBRepoImpl")
public class ProductDBRepoImpl implements ProductRepository {

	@Autowired
	private MongoOperations operations;

	@Override
	public Map<String, Product> create(List<ProductDTO> products) {
		// TODO Auto-generated method stub
		Map<String, Product> createdProducts = new HashMap<String, Product>();
		for (ProductDTO productName : products) {

			Product existingProduct = getProductByName(productName.getName());

			if (existingProduct == null) {
				Product product = new Product(productName.getName());
				operations.save(product);
				createdProducts.put(product.getId(), product);
			}else {
				createdProducts.put(existingProduct.getId(), existingProduct);
			}

		}
		return createdProducts;
	}

	@Override
	public List<Product> getAllProducts() {
		return operations.findAll(Product.class);
	}

	@Override
	public Product getProductById(String id) {
		// TODO Auto-generated method stub
		Query searchQuery = new Query(Criteria.where("_id").is(id));
		return operations.findOne(searchQuery, Product.class);
	}

	@Override
	public Product getProductByName(String name) {
		// TODO Auto-generated method stub
		Query searchQuery = new Query(Criteria.where("name").is(name));
		return operations.findOne(searchQuery, Product.class);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		Query deleteQuery = new Query(Criteria.where("_id").is(id));
		WriteResult result = operations.remove(deleteQuery, Product.class);
		return result.getN();
	}

	@Override
	public int patchUpdate(String id, String name) {
		// TODO Auto-generated method stub
		Query patchQuery = new Query(Criteria.where("_id").is(id));
		Update update = new Update();
		update.set("name", name);
		WriteResult result = operations.updateFirst(patchQuery, update, Product.class);
		return result.getN();
	}

}
