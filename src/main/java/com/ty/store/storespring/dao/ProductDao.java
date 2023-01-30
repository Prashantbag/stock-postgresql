package com.ty.store.storespring.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.store.storespring.dto.Product;
import com.ty.store.storespring.repository.ProductRepository;

@Repository
public class ProductDao {

	@Autowired
	ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findProductbyid(int id) {
		return productRepository.findById(id);
	}

	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public Product getProductByName(String productName) {
		return productRepository.getProductByName(productName);
	}
}
