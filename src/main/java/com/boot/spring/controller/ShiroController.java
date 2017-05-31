package com.boot.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShiroController {

	@GetMapping({ "/", "/index" })
	public String index() {

		return "index";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request) {
		String exception = (String) request.getAttribute("shiroLoginFailure");
		String msg = "";
		if (exception != null) {
			if (UnknownAccountException.class.getName().equals(exception)) {

				System.out.println("UnknownAccountException -- > 账号不存在：");

				msg = "UnknownAccountException -- > 账号不存在：";

			} else if (IncorrectCredentialsException.class.getName().equals(exception)) {

				System.out.println("IncorrectCredentialsException -- > 密码不正确：");

				msg = "IncorrectCredentialsException -- > 密码不正确：";

			} else if ("kaptchaValidateFailed".equals(exception)) {

				System.out.println("kaptchaValidateFailed -- > 验证码错误");

				msg = "kaptchaValidateFailed -- > 验证码错误";

			} else {

				msg = "else >> " + exception;

				System.out.println("else -- >" + exception);

			}
		}

		return "index";
	}
}
