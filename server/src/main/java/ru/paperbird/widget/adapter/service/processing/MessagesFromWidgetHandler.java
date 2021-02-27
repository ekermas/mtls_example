package ru.paperbird.widget.adapter.service.processing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
@Slf4j
public class MessagesFromWidgetHandler {

    private final RestTemplate restTemplate;

    public MessagesFromWidgetHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String testGet() {
        ResponseEntity<String> getResponse =
                restTemplate.getForEntity("https://api-gateway:443", String.class);
        return getResponse.getBody();
    }
    
    public String handlePostRequest(
            String src
    ) throws Exception {
        log.debug("{} {}",
                kv("method", "handlePostRequest"),
                kv("src", src)
        );
        String res = testGet();
        log.debug("{} {}",
                kv("method", "handlePostRequest"),
                kv("res", res)
        );
        return res;
    }

}
