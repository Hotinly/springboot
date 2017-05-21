package com.boot.spring.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.spring.entity.ClientMsg;
import com.boot.spring.entity.ServerMsg;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

	@RequestMapping("/init")
	public String page2(HttpServletRequest request) {
		request.getSession().setAttribute("userId", new Random().nextLong());
		return "websocket2";
	}


//============↓↓↓↓============\\
	@RequestMapping("/page")
	public String page() {
		return "websocket";
	}

	@MessageMapping("/toServer")
	@SendTo(value = "/topic/getResponse")
	public ServerMsg serverResponse(ClientMsg msg) {
		ServerMsg smsg = new ServerMsg();
		smsg.setMsg_from(msg.getMsg_from());
		smsg.setMsg_to(msg.getMsg_to());
		smsg.setMsg_content("SERVER:" + msg.getMsg_content());
		smsg.setMsg_date(msg.getMsg_date());
		return smsg;
	}

}
