package com.ty.store.storespring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.store.storespring.dao.ProductDao;
import com.ty.store.storespring.dto.Product;
import com.ty.store.storespring.exception.NoSuchIdFoundException;
import com.ty.store.storespring.util.ResponseStructure;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {

		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();

		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Product Saved");
		responseStructure.setData(productDao.saveProduct(product));

		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(int id) {

		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();

		Optional<Product> product = productDao.findProductbyid(id);

		if (product.isPresent()) {

			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product Found");
			responseStructure.setData(product.get());
		}else {
			throw new NoSuchIdFoundException();
		}

		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product, int id) {

		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();

		Optional<Product> existingProduct = productDao.findProductbyid(id);

		if (existingProduct.isPresent()) {
			product.setId(id);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Product Update");
			responseStructure.setData(productDao.updateProduct(product));
		}else {
			throw new NoSuchIdFoundException();
		}

		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int id) {

		ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();

		Optional<Product> product = productDao.findProductbyid(id);

		if (product.isPresent()) {
			productDao.deleteProduct(product.get());
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Product Saved");
			responseStructure.setData(product.get());
		}else {
			throw new NoSuchIdFoundException();
		}
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.FOUND);
	}
}
