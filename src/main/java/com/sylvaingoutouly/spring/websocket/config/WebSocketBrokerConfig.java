/**
 * 
 */
package com.sylvaingoutouly.spring.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


public class WebSocketBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override 
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ping").withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app")
				.enableSimpleBroker("/topic", "/queue");
	}

}
