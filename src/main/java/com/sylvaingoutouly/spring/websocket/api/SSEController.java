package com.sylvaingoutouly.spring.websocket.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by sylvain on 23/04/2016.
 *
 * @see Sebastien Deleuze Github : https://github.com/sdeleuze/spring-react-isomorphic/blob/master/src/main/java/io/spring/isomorphic/CommentController.java
 */
@RestController
@RequestMapping(value = "/sse")
public class SseController {

    /**
     * Hold all SseEmitter instances
     * CopyOnWriteArrayList is best when list is mainly accessed for reading and less for writing
     */
    private CopyOnWriteArrayList<SseEmitter> sseEmitters = new CopyOnWriteArrayList<>();

    /**
     * EndPoint de connection au SSE
     *
     * @return SseEmitter Le stream
     */
    @RequestMapping("/connect")
    public SseEmitter connect() {
        SseEmitter sseEmitter = new SseEmitter();
        this.sseEmitters.add(sseEmitter);
        sseEmitter.onCompletion(() -> this.sseEmitters.remove(sseEmitter));
        return sseEmitter;
    }

    /**
     * Ecrit dans le stream
     * @throws IOException
     */
    @RequestMapping(value = "/write", method = GET)
    public void write() {
        for (SseEmitter sseEmitter : this.sseEmitters) {
            // Servlet containers don't always detect ghost connection, so we must catch exceptions ...
            // Is it always needed ?
            try {
                sseEmitter.send("coucou");
            } catch (Exception e) {

            }
        }
    }
}
