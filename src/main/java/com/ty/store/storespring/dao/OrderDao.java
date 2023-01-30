package com.ty.store.storespring.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.store.storespring.dto.Orders;
import com.ty.store.storespring.repository.OrderRepository;


@Repository
public class OrderDao {

	@Autowired
	OrderRepository orderRepository;
	
	public Orders saveOrder(Orders order) {
		return orderRepository.save(order);
	}

	public Orders updateOrder(Orders order) {
		return orderRepository.save(order);
	}

	public Optional<Orders> findOrderbyid(int id) {
		return orderRepository.findById(id);
	}

	public void deleteOrder(Orders order) {
		orderRepository.delete(order);
	}
}

