package com.boot.spring.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.boot.spring.entity.OrderRedis;

@Repository
public class OrderRedisDao {

	// @Autowired

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Cacheable(value = "order", key = "'rds_db_'+#id")
	public OrderRedis testRedis2(String id) {
		OrderRedis order = null;
		String sql = "select * from t_order_jpa where order_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, new Object[] { id });
		while (rows.next()) {
			order = new OrderRedis();
			order.setId(rows.getString("order_id"));
			order.setNo(rows.getString("order_no"));
			order.setDate(rows.getDate("order_date"));
			order.setQuantity(rows.getInt("quantity"));
		}
		return order;
	}

	@Cacheable(value = "order", key = "'rds_'+#id")
	public OrderRedis testRedis(String id){
		OrderRedis o = new OrderRedis();
		o.setId(id);
		o.setNo("No.001");
		o.setDate(new Date());
		o.setQuantity(100);
		return o;
	}

	@Cacheable(value = "params:param:002")
	public String testRedis4(String value) {
		return value;
	}
	@Cacheable(value = "testRedis5", condition = "#value.length() < 11")
	public String testRedis5(String value) {
		return value;
	}
	@Cacheable(value = "testRedis6", key = "'testRedis6_'+#value", condition = "#value.length() < 6")
	public String testRedis6(String value) {
		return value;
	}

}
