package com.sylvaingoutouly.spring.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.util.logging.Logger;

/**
 * Configuration de WebSocket avec STOMP. Implique l'usage d'un brocker pour router
 * les message vers des destinataires différents (clients connectés, contrôleur ou autre)
 * en fonction d'un entête de type destinataire placé dans l'enveloppe STOMP
 */
@Configuration
@EnableWebSocketMessageBroker
public class BrockerWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    private static final Logger LOG = Logger.getLogger(BrockerWebSocketConfig.class.getName());

    /**
     * Déclare tous les endPoints accessibles aux clients webSocket
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/servicesSocket");
    }


    /**
     *
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
    }


}
