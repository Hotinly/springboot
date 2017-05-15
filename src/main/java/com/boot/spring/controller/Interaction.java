package com.boot.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.spring.entity.PostEntity;

@Controller
@RequestMapping("/lab")
public class Interaction {

	@RequestMapping("/view/{username}")
	public String view(@PathVariable String username) {
		// <p th:text="'Hello, ' + ${username} + '!'" /> will receive the value
		return "dataTransfer";
	}

	@RequestMapping(value = "/postData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> postData(String number, int quantity, String date) {
		Map<String, Object> map = new HashMap<>();
		map.put("handler", "postData");
		map.put("number", number);
		map.put("quantity", quantity);
		map.put("date", date);
		return map;
	}

	@PostMapping("/postJson")
	public @ResponseBody Map<String, Object> postJson(@RequestBody PostEntity ety) {
		Map<String, Object> map = new HashMap<>();
		map.put("handler", "postJson");
		map.put("number", ety.getNumber());
		map.put("quantity", ety.getQuantity());
		map.put("date", ety.getDate());
		return map;
	}
}
