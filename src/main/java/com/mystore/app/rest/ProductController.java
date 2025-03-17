package com.mystore.app.rest;

import com.mystore.app.entity.Product;
import com.mystore.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody @Valid Product product) {
        Product p = productService.addProduct(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product p = productService.getProduct(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @Valid @RequestBody Product product) {
        Product p = productService.updateProduct(id, product);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        String message = productService.deleteProduct(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // TODO: API to search products by name
    @GetMapping("/search")
    public ResponseEntity<Product> getProductByName(@RequestParam ("name") String name){
    	Product p = productService.findByName(name);
    	
    	if(p==null) {
    		return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    	}
    	
    		return new ResponseEntity<>(p, HttpStatus.OK);
    
    }

    // TODO: API to filter products by category
    @GetMapping("/filter/category")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam ("category") String category){
    	System.out.println("inside category filter");
    	List<Product> p = productService.findProductByCategory(category);
    	
    	if(p.isEmpty()) {
    		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	}
    	
    	return new ResponseEntity<List<Product>>(p, HttpStatus.OK);
    	
    }


	/*
	 * // TODO: API to filter products by price range
	 * 
	 * @GetMapping("/filter/price") public ResponseEntity<List<Product>>
	 * getProductByPriceRange(@RequestParam ("minPrice") double
	 * minPrice, @RequestParam("maxPrice") double maxPrice) {
	 * System.out.println("inside price filter"); List<Product> p =
	 * productService.filterByPriceRange(minPrice, maxPrice); if(p.isEmpty()) {
	 * return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); }
	 * 
	 * return new ResponseEntity<List<Product>>(p, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // TODO: API to filter products by stock quantity range
	 * 
	 * @GetMapping("/filter/stock") public ResponseEntity<List<Product>>
	 * getProductByStockRange(@RequestParam ("minStock") double
	 * minStock, @RequestParam("maxStock") double maxStock) { List<Product> p =
	 * productService.filterByStockRange(minStock, maxStock); if(p.isEmpty()) {
	 * return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); }
	 * 
	 * return new ResponseEntity<List<Product>>(p, HttpStatus.OK);
	 * 
	 * }
	 */

}
