package com.boot.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/nlb")
public class NLBAndSessionController {

	private static final String STR_SESSION_KEY = "name";

	@GetMapping("/")
    public String index() {
        return "session";
    }

	@GetMapping("/setSession")
	public @ResponseBody Map<String, Object> setSession(String value, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<>();
		req.getSession().setAttribute(STR_SESSION_KEY, value);
		map.put("msg", "Session setted");
		return map;
	}

	@GetMapping("/getSession")
	public @ResponseBody Map<String, Object> getSession(HttpServletRequest req){
		Map<String, Object> map = new HashMap<>();
		HttpSession session = req.getSession();
		Object value = session.getAttribute(STR_SESSION_KEY);
		map.put("value", value);
		map.put("sessionId", session.getId());
		map.put("port", req.getLocalPort());
		map.put("msg", "Got session");
		return map;
	}
}
