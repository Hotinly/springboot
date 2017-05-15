package com.boot.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.spring.entity.JdbcProperties;

@RestController
@RequestMapping("/lab")
public class Lab {

	@RequestMapping("/hello")
	public String hello() {
		return "Hello spring boot!";
	}

	@Value("${user.name}")
	private String name;
	@Value("${user.description}")
	private String description;

	@RequestMapping("properties")
	public String readByValue() {
		return name + ":" + description;
	}
	@Autowired
	private Environment env;
	@RequestMapping("properties2")
	public String readFromEnv(){
		System.out.println("默认只能直接读取application.properties:" + env.getProperty("user.description"));
		//@Configuration + @PropertySource("PATH") 把Properties文件信息添加到Environment中
		return env.getProperty("spring.thymeleaf.cache");  
	}
	@Autowired
	private JdbcProperties jdbcProp;
	@RequestMapping("properties3")
	public String readFromCustomed(){
		//通过实体类读取。把Properties文件与实体类定义的各属性对应,还需定义@ConfigurationProperties(prefix = "jdbc")
		return jdbcProp.toString();
	}

	@RequestMapping("/pathvariable/{num}")
	public String pathVariable(@PathVariable("num") String num) {
		return "<p>PathVariable is <b style='color:red'>" + num + "</b></p>";
	}

	@RequestMapping(value = "/getpost", method = RequestMethod.GET)
	public String get() {
		return "RequestMethod.GET";
	}
	@RequestMapping(value = "/getpost", method = RequestMethod.POST)
	public String post() {
		return "RequestMethod.POST";
	}

}