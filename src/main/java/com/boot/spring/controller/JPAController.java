package com.boot.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.dao.OrderJPADao;
import com.boot.spring.entity.OrderJPA;

@Controller
@RequestMapping("/jpa")
public class JPAController {
	
	@InitBinder		//????
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

	@Autowired
	private OrderJPADao jpaDao;

	@GetMapping("/getById")
	public @ResponseBody OrderJPA getById(String id) {
		return jpaDao.getById(id);
	}

	@GetMapping("/findAll")
	public @ResponseBody Object findAll() {
		return jpaDao.findAll();
	}

	@PostMapping("/insert")
	public @ResponseBody String insert(@RequestBody OrderJPA orderJPA) {
		try {
			jpaDao.insert(orderJPA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Insert";
	}

	@PostMapping("/update")
	public @ResponseBody String update(@RequestBody OrderJPA orderJPA) {
		jpaDao.update(orderJPA);
		return "Update";
	}

	@PostMapping("/delById")
	public @ResponseBody String delById(String id) {
		jpaDao.delById(id);
		return "Delete";
	}

// JPA 高级查询 \\
	@GetMapping("/like")
	public @ResponseBody Object like(String orderNo) {
		return jpaDao.likeOrderNo("%" + orderNo + "%");
	}

	@GetMapping("/lessThan")
	public @ResponseBody Object lessThanQuantity(int quantity) {
		return jpaDao.lessThanQuantity(quantity);
	}
}
