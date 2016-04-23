package com.sylvaingoutouly.spring.websocket.api;

import com.sylvaingoutouly.spring.websocket.service.FirstSse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by sylvain on 23/04/2016.
 */
@RestController
@RequestMapping(value = "/sse")
public class SseController {

    @Autowired
    FirstSse sse;

    /**
     * EndPoint de connection au SSE
     * @return SseEmitter Le stream
     */
    @RequestMapping(value = "/connect", method = GET)
    public SseEmitter stream() {
        return sse;
    }

    /**
     * Ecrit dans le stream
     * @throws IOException
     */
    @RequestMapping(value = "/write", method = GET)
    public void write() throws IOException {
        sse.send("coucou");
        //sse.complete();
    }

}
