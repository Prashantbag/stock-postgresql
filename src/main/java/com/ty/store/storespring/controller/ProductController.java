package com.ty.store.storespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.store.storespring.dto.Product;
import com.ty.store.storespring.service.ProductService;
import com.ty.store.storespring.util.ResponseStructure;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productservice;

	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product){
		return productservice.saveProduct(product);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Product>> getProductById(@RequestParam int id){
		return productservice.getProductById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product,@RequestParam int id){
		return productservice.updateProduct(product, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable int id){
		return productservice.deleteProduct(id);
	}
	
}
