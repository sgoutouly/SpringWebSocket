	package com.sylvaingoutouly.spring.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.sylvaingoutouly.spring.websocket.handler.PingWebSocketHandler;

@Configuration
@EnableWebSocket 
public class WebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/ping");		
	}

	@Bean
	PingWebSocketHandler webSocketHandler() {
		return new PingWebSocketHandler();
	}
	
}
