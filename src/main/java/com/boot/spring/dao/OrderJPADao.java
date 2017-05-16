package com.boot.spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.boot.spring.entity.OrderJPA;

@Repository
@Transactional(rollbackFor = Throwable.class, readOnly = false)
public class OrderJPADao {

	@Autowired
	private OrderJPARepository jpaRepo;

	public OrderJPA getById(String id) {
		return jpaRepo.findOne(id);
	}

	public List<OrderJPA> findAll() {
		return jpaRepo.findAll();
	}

	public void insert(OrderJPA orderJPA) throws Exception {
		OrderJPA entity = jpaRepo.save(orderJPA);
		System.out.println(entity.equals(orderJPA));
//		if(!StringUtils.isNullOrEmpty(orderJPA.getId()))
//			throw new Exception("Make exception on purpose !");
		System.out.println("Insert: " + entity.getId());
	}

	public void update(OrderJPA orderJPA) {
		OrderJPA entity = jpaRepo.save(orderJPA);
		System.out.println("Update: " + entity.getId());
	}

	public void delById(String id) {
		jpaRepo.delete(id);
	}

// JPA 高级查询 \\
	public List<OrderJPA> likeOrderNo(String orderNo) {
		return jpaRepo.findAllByNoLike("%" + orderNo + "%");
	}

	public List<OrderJPA> lessThanQuantity(int quantity) {
		return jpaRepo.findAllByQuantityLessThan(quantity);
	}
}
