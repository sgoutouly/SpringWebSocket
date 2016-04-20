package com.sylvaingoutouly.spring.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

/**
 * Configuration basique de WebSocket
 * Pas de sockJS (pas de fallback sir le browser ne supporte pas WebSocket)
 * Pas de broker (pas e stratégie de routage des message vers des applications, user ou gestionnaire de Q)
 *
 * Contient une implémentation de handler chargé de broadcaster un message
 */
@Configuration
@EnableWebSocket
public class BrockerWebSocketConfig implements WebSocketConfigurer {

    private static final Logger LOG = Logger.getLogger(BrockerWebSocketConfig.class.getName());

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/broadcast").withSockJS();
    }

    @Bean
    BroadcastWebSocketHandler webSocketHandler() {
        return new BroadcastWebSocketHandler();
    }

    /**
     * Handler permettant de broadcaster un message sur des WebSocket
     * Handler basique chargé de lister toutes les sessions WebSocket ouvertes et de router chaque message
     * textuel reçu vers la totalité des sessions listées
     */
    private static class BroadcastWebSocketHandler extends TextWebSocketHandler {

        /** Les sessions Websocket acives */
        private List<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();

        /**
         * Appelé à chaque connexion étable
         * @param session
         * @throws Exception
         */
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            LOG.info("Nouvelle connexion : [" + session.getId() + "]");
            sessions.add(session);
        }

        /**
         * Appelé à chaque connexion fermée
         * @param session
         * @param status
         * @throws Exception
         */
        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
            LOG.info("Fermeture de connection " + session.getId() + ": [" + status + "]");
            sessions.remove(session);
        }

        /**
         * Appelé à chaque message ascendant arrivé sur le end point
         * @param session
         * @param message
         * @throws Exception
         */
        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            LOG.info("Message à broadcaster : " + message);
            for (WebSocketSession s : sessions) {
                s.sendMessage(message);
            }
        }
    }

}
