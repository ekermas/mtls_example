package ru.paperbird.widget.adapter.hexagon.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class EchoApiModel {

    @JsonIgnoreProperties({"discriminator"})
    public interface Message {
        String getDiscriminator();
    }

    @Data
    @AllArgsConstructor
    @JsonIgnoreProperties({"discriminator"})
    public static class Request implements Message {
        final String id;
        final Long ts;
        final String text;

        public String getDiscriminator() {
            return "rq";
        }
    }

    @Data
    @JsonIgnoreProperties({"discriminator"})
    @Builder
    public static class Response implements Message {
        final String id;
        final @Builder.Default
        Long ts = System.currentTimeMillis();
        final String text;

        public String getDiscriminator() {
            return "rs";
        }
    }

}
