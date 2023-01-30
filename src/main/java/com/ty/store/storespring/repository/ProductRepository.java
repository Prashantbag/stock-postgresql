package com.ty.store.storespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.store.storespring.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	public Product getProductByName(String name);
}
