package com.ty.store.storespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.store.storespring.dto.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
