package com.boot.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.spring.dao.OrderDao;
import com.boot.spring.entity.Order;
import com.boot.spring.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public List<Order> findAll() {
		return orderDao.findAll();
	}

}
