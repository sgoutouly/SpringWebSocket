package com.sylvaingoutouly.spring.websocket.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Created by sylvain on 23/04/2016.
 */
@Service @NoArgsConstructor
public class FirstSse extends SseEmitter {
}
