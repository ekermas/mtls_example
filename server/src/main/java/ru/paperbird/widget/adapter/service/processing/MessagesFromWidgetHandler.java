package ru.paperbird.widget.adapter.service.processing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@Slf4j
public class MessagesFromWidgetHandler {

    public String handlePostRequest(
            String src
    ) throws Exception {
        log.debug("{} {}",
                kv("method", "handlePostRequest"),
                kv("src", src)
        );
        String res = "success";
        log.debug("{} {}",
                kv("method", "handlePostRequest"),
                kv("res", res)
        );
        return res;
    }

}
