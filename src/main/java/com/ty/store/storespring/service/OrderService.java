package com.ty.store.storespring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.store.storespring.dao.OrderDao;
import com.ty.store.storespring.dao.ProductDao;
import com.ty.store.storespring.dto.Orders;
import com.ty.store.storespring.dto.Product;
import com.ty.store.storespring.exception.NoSuchIdFoundException;
import com.ty.store.storespring.util.ResponseStructure;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	ProductDao productDao;
	
	

	public ResponseEntity<ResponseStructure<Orders>> saveOrder(Orders order) {
		ResponseStructure<Orders> responseStructure = new ResponseStructure<Orders>();


		orderDao.saveOrder(order);
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Order Saved");
		responseStructure.setData(orderDao.saveOrder(order));

		return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Orders>> getOrderById(int id) {
		ResponseStructure<Orders> responseStructure = new ResponseStructure<Orders>();

		Optional<Orders> order = orderDao.findOrderbyid(id);

		if (order.isPresent()) {

			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Order Saved");
			responseStructure.setData(order.get());
		} else {
			throw new NoSuchIdFoundException();
		}

		return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Orders>> updateOrder(Orders order ,int orderId, int productId) {
		ResponseStructure<Orders> responseStructure = new ResponseStructure<Orders>();

		Optional<Orders> existingOrder = orderDao.findOrderbyid(orderId);
		Optional<Product> fetchProduct = productDao.findProductbyid(productId);
		//Product newProduct= new Product();
		if (existingOrder.isPresent()) {
			Orders orderObj= existingOrder.get();
			double totalPrice = 0;
				if (fetchProduct.isPresent()) {
					List<Product> items = new ArrayList<Product>();
					List<Product> orderedProduct = order.getProducts();
					int existingQuantity=fetchProduct.get().getQuantity();
					
					for (Product product : orderedProduct) {

						
						totalPrice += fetchProduct.get().getPrice();
						fetchProduct.get().setQuantity(existingQuantity-product.getQuantity());
						items.add(fetchProduct.get());
						
					}
					order.setId(orderId);	
					
					order.setTotalPrice(totalPrice);
					order.setPriceWithGst(totalPrice * 0.18);
					order.setProducts(items);
					orderDao.updateOrder(order);
					fetchProduct.get().setId(productId);
					productDao.updateProduct(fetchProduct.get());
					responseStructure.setStatus(HttpStatus.CREATED.value());
					responseStructure.setMessage("Order Saved");
					responseStructure.setData(orderDao.updateOrder(order));
				}
			
		} else {
			throw new NoSuchIdFoundException();
		}
		
		
		
		
		return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Orders>> deleteOrder(int id) {
		ResponseStructure<Orders> responseStructure = new ResponseStructure<Orders>();

		Optional<Orders> existingOrder = orderDao.findOrderbyid(id);

		if (existingOrder.isPresent()) {
			orderDao.deleteOrder(existingOrder.get());
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Order Saved");
			responseStructure.setData(existingOrder.get());
		} else {
			throw new NoSuchIdFoundException();
		}

		return new ResponseEntity<ResponseStructure<Orders>>(responseStructure, HttpStatus.CREATED);

	}

}
