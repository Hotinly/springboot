package com.boot.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalExceptionHandler {

	@RequestMapping("/403")
	public String unAuthorized() {
		return "403";
	}

	@RequestMapping("/404")
	public String notFound() {
		return "404";
	}
	
	@RequestMapping("/500")
	public String interServerError() {
		return "500";
	}
	
}
