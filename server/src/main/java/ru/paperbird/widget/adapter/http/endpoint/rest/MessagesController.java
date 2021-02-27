package ru.paperbird.widget.adapter.http.endpoint.rest;

import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping(path = "/api/echo")
    public String getEcho() {
        log.debug("{}",
                kv("method", "getEcho")
        );
        try {
            return messagesFromWidgetHandler.handlePostRequest("");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ex.getMessage();
        }
    }

}