package com.eshopping;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
	    private List<Product> products;
	    private int nextId;

	    public ProductService() {
	        products = new ArrayList<>();
	        nextId = 1;
	    }

	    public void addProduct(String name, double price, String description) {
	        products.add(new Product(nextId++, name, price, description));
	    }

	    public void updateProduct(int id, String name, double price, String description) {
	        for (Product product : products) {
	            if (product.getId() == id) {
	                product.setName(name);
	                product.setPrice(price);
	                product.setDescription(description);
	                break;
	            }
	        }
	    }

	    public void deleteProduct(int id) {
	        products.removeIf(product -> product.getId() == id);
	    }

	    public List<Product> getAllProducts() {
	        return new ArrayList<>(products);
	    }
}
