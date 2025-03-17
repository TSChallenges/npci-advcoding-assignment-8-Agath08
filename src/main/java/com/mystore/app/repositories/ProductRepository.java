package com.mystore.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystore.app.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {

   List<Product> findByCategory(String category);

}
