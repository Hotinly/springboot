package com.boot.spring.service;

import java.util.List;

import com.boot.spring.entity.Order;

public interface OrderService {

	public List<Order> findAll();
}
