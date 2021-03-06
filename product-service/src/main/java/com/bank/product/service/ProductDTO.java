/**
 * 
 */
package com.bank.product.service;

import java.io.Serializable;

/**
 * 
 *
 */
public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [name=" + name +"]";
	}

	public ProductDTO() {

	}

	public ProductDTO(String name) {
		super();
		this.name = name;
	}

}
