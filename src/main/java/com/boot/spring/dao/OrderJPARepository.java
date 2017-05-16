package com.boot.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.spring.entity.OrderJPA;

public interface OrderJPARepository extends JpaRepository<OrderJPA, String> {

	/**
	 * 在控制器中直接依赖注入了OrderJPARepository，并没有OrderJPARepository接口的实现类。
	 * 在没有写实现类的情况下不报错吗？答案是并不报错，因为这些事儿都交给了JPA去做。那么为什么要用JPA，答案也只有2个字“方便”。
	 * 继承 JpaRepository 后， 基本增删改查已经有了。
	 * http://www.cnblogs.com/GoodHelper/p/6227944.html
	 */

	// 根据order_no like 模糊查询
	List<OrderJPA> findAllByNoLike(String no);

	// 根据 数量 进行小于 查询
	List<OrderJPA> findAllByQuantityLessThan(int quantity);
}
