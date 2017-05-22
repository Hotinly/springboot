package com.boot.spring.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.boot.spring.entity.Message;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/ws").addInterceptors(handshakeInterceptor);

	}

	HandshakeInterceptor handshakeInterceptor = new HandshakeInterceptor() {

		@Override
		public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
				WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
			System.out.println("==============BEFOREHANDSHAKE============");
			if (request instanceof ServletServerHttpRequest) {
				ServletServerHttpRequest sshr = (ServletServerHttpRequest) request;
				HttpSession session = sshr.getServletRequest().getSession();
				Long userId = (Long) session.getAttribute("userId");
				if (userId != null) {
					attributes.put("userId", userId);
				} else {
					return false;
				}
				System.out.println("====" + userId + "====");
				return true;
			}
			return false;
		}

		@Override
		public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
				Exception exception) {
			System.out.println("==============AFTERHANDSHAKE============");
		}
	};

	public static final Map<Long, WebSocketSession> userSocketSessionMap;
	static {
		userSocketSessionMap = new HashMap<Long, WebSocketSession>();
	}

	WebSocketHandler webSocketHandler = new WebSocketHandler() {

		@Override
		public boolean supportsPartialMessages() {
			System.out.println("========SUPPORTSPARTIALMESSAGES========");
			return false;
		}

		@Override
		public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
			// 前台js如果不主动调用 WebSocket 的close方法，关闭连接，后台这里会有异常（java.io.IOException: 您的主机中的软件中止了一个已建立的连接。）
			System.out.println("========HANDLETRANSPORTERROR========");
			System.out.println("===="+exception.getMessage()+"====");
			if (session.isOpen()) {
				session.close();
			}
			Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, WebSocketSession> entry = it.next();
				if (entry.getValue().getId().equals(session.getId())) {
					userSocketSessionMap.remove(entry.getKey());
					System.out.println("用户ID [" + entry.getKey() + "] 消息传输错误，当前WebSocketSession会话已移除！");
					break;
				}
			}
		}

		@Override
		public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
			System.out.println("========HANDLEMESSAGE========");
			if (message.getPayloadLength() == 0)
				return;
			System.out.println(message.getPayload().toString());
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			// mapper.setTimeZone(TimeZone.getTimeZone("GMT+:08:00"));
			// DateFormat dateFormat = new
			// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.CHINA);
			// mapper.setDateFormat(dateFormat);
			// mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
			// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			Message msg = mapper.readValue(message.getPayload().toString(), Message.class);

			// WebSocketSession wsSession = userSocketSessionMap.get(msg.getMsg_to()); //Normal

			Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
			Entry<Long, WebSocketSession> entry = null;
			while (it.hasNext()) {
				entry = it.next();
			}
			WebSocketSession wsSession = entry.getValue();

			if (wsSession != null && wsSession.isOpen()) {
				wsSession.sendMessage(new TextMessage(mapper.writeValueAsString(msg)));
			}
		}

		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			System.out.println("========AFTERCONNECTIONESTABLISHED========");
			Long userId = (Long) session.getAttributes().get("userId");
			if (userSocketSessionMap.get("userId") == null) {
				userSocketSessionMap.put(userId, session);
			}
		}

		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
			System.out.println("========AFTERCONNECTIONCLOSED========");
			// System.out.println(session.isOpen());  //false
			System.out.println("===="+closeStatus.getCode()+":"+closeStatus.getReason()+"====");
			Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, WebSocketSession> entry = it.next();
				if (entry.getValue().getId().equals(session.getId())) {
					userSocketSessionMap.remove(entry.getKey());
					System.out.println("用户ID [" + entry.getKey() + "] 连接关闭，当前WebSocketSession会话已移除！");
					break;
				}
			}
		}
	};

}

// ============↓↓↓↓============\\
// @Configuration
// @EnableWebSocketMessageBroker // 启用基于WebSocket的更高级别的代理消息传输协议
// public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer
// {
//
// /**
// * 注册 STOMP 协议节点，并映射到指定的 URL
// */
// @Override
// public void registerStompEndpoints(StompEndpointRegistry registry) {
// registry.addEndpoint("/endpointSocket").withSockJS();
// }
//
// /**
// * 配置消息代理
// */
// @Override
// public void configureMessageBroker(MessageBrokerRegistry registry) {
// super.configureMessageBroker(registry);
// registry.enableSimpleBroker("/topic");
// }
// }
