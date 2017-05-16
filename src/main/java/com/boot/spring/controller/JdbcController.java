package com.boot.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.dao.OrderDao;
import com.boot.spring.entity.Order;
import com.boot.spring.service.OrderService;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping("/jdbc")
public class JdbcController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/findAll")
	public @ResponseBody List<Order> findAll(){
		return orderService.findAll();
	}
	
	@Autowired
	private OrderDao orderDao;
	
	@RequestMapping("/findAll2")
	public @ResponseBody Object findAll2(){
		return orderDao.findAll2();
	}

	@RequestMapping("/getById")
	public @ResponseBody Order getById(String id){
		return orderDao.getById(id);
	}
	
	@RequestMapping("/save")
	public @ResponseBody String saveOrUpdate(@RequestBody Order order){
		String str = null;
		if(StringUtils.isNullOrEmpty(order.getId())){
			orderDao.insert(order);
			str = "Insert OK";
		} else{
			orderDao.update(order);
			str = "Update OK";
		}
		return str;
	}
	
}
