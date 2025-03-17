package com.mystore.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mystore.app.entity.Product;
import com.mystore.app.repositories.ProductRepository;

@Service
public class ProductService {
	
	//Random random = new Random();
	
    private Integer currentId = 1;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
    	System.out.println("inside add prod service");
        product.setId(currentId++);
        productRepository.save(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.get();
    }

    public Product updateProduct(Integer id, Product product) {
        Product p = productRepository.findById(id).get();
        if (p == null) return null;
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategory(product.getCategory());
        p.setStockQuantity(product.getStockQuantity());
        productRepository.save(p);
        return p;
    }

    public String deleteProduct(Integer id) {
        Product p = productRepository.findById(id).get();
        if (p == null) return "Product Not Found";
        productRepository.delete(p);
        return "Product Deleted Successfully";
    }

    // TODO: Method to search products by name
    public Product findByName(String name) {
    	List<Product> products = productRepository.findAll();
    	if (!name.equals(null)) {
    		for (Product ele:products) {
    			if (name.equalsIgnoreCase(ele.getName())) {
    				return ele;
    			}
    		}
    	}
    	return null;
    }


    // TODO: Method to filter products by category
  public List<Product> findProductByCategory(String category) {
    	
    	List<Product> products2 =productRepository.findByCategory(category);
    	
    	return products2;
    }
    
    // TODO: Method to filter products by price range
	/*
	 * public List<Product> filterByPriceRange(double minPrice,double maxPrice) {
	 * List<Product> Products3 = new ArrayList<Product>(); for (Product
	 * ele:Products3) { if (ele.getPrice()>=minPrice && ele.getPrice()<=maxPrice) {
	 * 
	 * Products3.add(ele);
	 * 
	 * }
	 * 
	 * 
	 * } return Products3; }
	 * 
	 * // TODO: Method to filter products by stock quantity range public
	 * List<Product> filterByStockRange(double minStock,double maxStock) {
	 * List<Product> Products4 = new ArrayList<Product>(); for (Product
	 * ele:Products4) { if (ele.getStockQuantity()>=minStock &&
	 * ele.getStockQuantity()<=maxStock) { Products4.add(ele); }
	 * 
	 * } return Products4; }
	 * 
	 */

}
