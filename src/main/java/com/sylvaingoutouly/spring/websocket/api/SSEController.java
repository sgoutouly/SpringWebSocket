package com.sylvaingoutouly.spring.websocket.api;

import com.sylvaingoutouly.spring.websocket.service.FirstSse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by sylvain on 23/04/2016.
 *
 * @see Sebastien Deleuze Github : https://github.com/sdeleuze/spring-react-isomorphic/blob/master/src/main/java/io/spring/isomorphic/CommentController.java
 */
@RestController
@RequestMapping(value = "/sse")
public class SseController {

    /** Hold all SseEmitter instances */
    private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<SseEmitter>());

    /**
     * EndPoint de connection au SSE
     * @return SseEmitter Le stream
     */
    @RequestMapping("/connect")
    public SseEmitter connect() {
        SseEmitter sseEmitter = new SseEmitter();
        synchronized (this.sseEmitters) { // Need to synchronize the list when you iterate it
            this.sseEmitters.add(sseEmitter);
            sseEmitter.onCompletion(() -> {
                synchronized (this.sseEmitters) {
                    this.sseEmitters.remove(sseEmitter);
                }
            });
        }
        return sseEmitter;
    }

    /**
     * Ecrit dans le stream
     * @throws IOException
     */
    @RequestMapping(value = "/write", method = GET)
    public void write() {
        synchronized (this.sseEmitters) { // Need to synchronize the list when you iterate it
            for (SseEmitter sseEmitter : this.sseEmitters) {
                // Servlet containers don't always detect ghost connection, so we must catch exceptions ...
                // Is it always needed ?
                try {
                    sseEmitter.send("coucou");
                } catch (Exception e) {}
            }
        }

    }

}
