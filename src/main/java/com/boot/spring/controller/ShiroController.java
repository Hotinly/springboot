package com.boot.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShiroController {

	@GetMapping({ "/", "/index" })
	public String index() {

		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(HttpServletRequest request, Map<String, String> map) {
		// UsernamePasswordToken token = new UsernamePasswordToken("", "");
		// Subject subject = SecurityUtils.getSubject();
		// subject.login(token);
		// Session session = subject.getSession();
		// session.setAttribute("token", token);
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
			map.put("msg", msg);
			return "login";
		}
		map.put("msg", "Login success!");
		return "index";
	}

	@GetMapping("/userList")
//	@RequiresRoles("vip")  //角色管理
	@RequiresRoles(value={"admin", "vip"}, logical= Logical.OR)
	@RequiresPermissions("userInfo:view") // 权限管理;
	public String userInfo() {
		return "userInfo";
	}

	@GetMapping("/userAdd")
	@RequiresPermissions("userInfo:add") // 权限管理;
	public String userInfoAdd() {
		return "userInfoAdd";
	}

	@GetMapping("/userDel")
	@RequiresPermissions("userInfo:del") // 权限管理;
	public String userInfoDel() {
		return "userInfoDel";
	}
}
