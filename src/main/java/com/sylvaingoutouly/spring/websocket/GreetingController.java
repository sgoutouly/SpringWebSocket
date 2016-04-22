package com.sylvaingoutouly.spring.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @MessageMapping("/touch")
    public String handle(String greeting) {
        return "coucou : " + greeting;
    }

}