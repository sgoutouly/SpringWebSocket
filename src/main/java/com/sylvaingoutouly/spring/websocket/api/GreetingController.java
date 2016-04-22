package com.sylvaingoutouly.spring.websocket.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class GreetingController {

    /** Permet d'écrire sur la socket depuis un service */
    @Autowired private SimpMessagingTemplate socket;

    /**
     * Permet de broadcaster un message vers tous les abonnés au channel
     * /topic/chat depuis une API
     */
    @RequestMapping(value="/broadcast", method = POST)
    public void broadcast(@RequestBody String message) {
        socket.convertAndSend("/topic/chat", message);
    }

    /**
     * Intercepte et fait suivre vers /topic/chat les messages adressés
     * à /app/chat
     *
     * Je ne suis pas parvenu à utiliser String comme argument de cette méthode
     * En effet c'est le message converter Jackson qui est systématiquemebt utilisé
     * avec l'annotation MessageMapping et ce dernier ne sait gérer que des objets JSON
     *
     * @param message
     * @return Message
     */
    @MessageMapping("/chat")
    public String chat(Message message) {
        return "[Handled by application] " + message.getBody();
    }

    /**
     * Type interne pour gérer la transformation des flux Json placé dans le
     * message STOMP (lié à la nécessité de passer du JSON à travers le broker applicatif)
     */
    @NoArgsConstructor @Getter
    private static class Message {
        private String body;
    }

}