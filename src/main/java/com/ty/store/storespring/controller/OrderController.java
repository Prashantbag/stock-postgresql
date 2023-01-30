package com.ty.store.storespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.store.storespring.dto.Orders;
import com.ty.store.storespring.service.OrderService;
import com.ty.store.storespring.util.ResponseStructure;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Orders>> saveOrder(@RequestBody Orders order){
		return orderService.saveOrder(order);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Orders>> getOrderById(@RequestParam int id){
		return orderService.getOrderById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Orders>> updateOrder(@RequestBody Orders order,@RequestParam int orderId,@RequestParam int productId){
		return orderService.updateOrder(order, orderId,productId);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Orders>> deleteOrder(@PathVariable int id){
		return orderService.deleteOrder(id);
	}
}
