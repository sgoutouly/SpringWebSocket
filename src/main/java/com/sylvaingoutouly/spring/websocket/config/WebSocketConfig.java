	package com.sylvaingoutouly.spring.websocket.config;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebSocket 
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new PingWebSocketHandler(), "/ping");		
	}

	static class PingWebSocketHandler extends TextWebSocketHandler {
		private List<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
		
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			System.out.println("Nouvelle connexion : [" + session.getId() + "]");
			sessions.add(session);
		}
		
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			System.out.println("Fermeture de connection " + session.getId() + ": [" + status + "]");
			sessions.remove(session);
		}
		
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			System.out.println(message);
			for (WebSocketSession s : sessions) {
				s.sendMessage(new TextMessage("salut les nains"));
			}
		}
	}

}
