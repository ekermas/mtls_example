package ru.paperbird.widget.adapter.http.endpoint.rest;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.paperbird.widget.adapter.service.processing.MessagesFromWidgetHandler;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@Timed
public class MessagesController {

    private final MessagesFromWidgetHandler messagesFromWidgetHandler;

    public MessagesController(MessagesFromWidgetHandler messagesFromWidgetHandler) {
        this.messagesFromWidgetHandler = messagesFromWidgetHandler;
    }

    @PostMapping(path = "/api/echo")
    public String postMessages(@RequestBody String src) {
        log.debug("{} {}",
                kv("method", "postMessages"),
                kv("src", src)
        );
        try {
            return messagesFromWidgetHandler.handlePostRequest(src);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return "success";
        }
    }

}