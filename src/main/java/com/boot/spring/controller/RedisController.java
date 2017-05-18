package com.boot.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.spring.dao.OrderRedisDao;
import com.boot.spring.entity.OrderRedis;

@RestController
@RequestMapping("/redis")
public class RedisController {

	private static final String STR_REDIS_KEY = "key:name";

	@Autowired
	private StringRedisTemplate redisTemplate;

	@GetMapping("/setRedis")
	public String setRedis(String value){
		redisTemplate.opsForValue().set(STR_REDIS_KEY, value);
		return "StringRedisTemplate.opsForValue().set(KEY, VALUE)";
	}
	@GetMapping("/getRedis")
	public String getRedis(String value){
		return redisTemplate.opsForValue().get(STR_REDIS_KEY);
	}


	@Autowired OrderRedisDao dao;

	@GetMapping("/testByNew")
	public OrderRedis test(String id){
		return dao.testRedis("rds_001");
	}
	@GetMapping("/testByDB")
	public OrderRedis test2(String id){
		return dao.testRedis2(id);
	}
	@GetMapping("/testParams")
	public String test3(String value){
		redisTemplate.opsForValue().set("params:param:001", value);	//层级: params < param < params:param:001
		return value;
	}
	@GetMapping("/testParams4")
	public String test4(String value){
		dao.testRedis4(value);
		return redisTemplate.opsForValue().get("params:param:003");
	}
	@GetMapping("/testParams5")
	public String test5(String value){
		dao.testRedis5(value);
		System.out.println(122);
		return redisTemplate.opsForValue().get("testRedis5");
	}
	@GetMapping("/testParams6")
	public String test6(String value){
		dao.testRedis6(value);
		return redisTemplate.opsForValue().get("zrange testRedis5~keys 0 -1"); //USELESS
	}
}
