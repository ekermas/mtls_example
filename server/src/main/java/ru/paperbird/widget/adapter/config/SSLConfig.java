package ru.paperbird.widget.adapter.config;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.HttpClient;

import javax.net.ssl.SSLContext;

@Configuration
public class SSLConfig {

    @Bean
    public SSLConnectionSocketFactory sslFactory(
            @Value("${client.ssl.key-store:}") Resource keyStore,
            @Value("${client.ssl.key-store-password:}") String keyStorePassword,
            @Value("${client.ssl.key-password:}") String keyPassword,
            @Value("${client.ssl.trust-store:}") Resource trustStore,
            @Value("${client.ssl.trust-store-password:}") String trustStorePassword) throws Exception {
        SSLContext sslcontext =
                SSLContexts.custom().loadTrustMaterial(
                        trustStore.getFile(),
                        trustStorePassword.toCharArray()
                ).loadKeyMaterial(
                        keyStore.getFile(),
                        keyStorePassword.toCharArray(),
                        keyPassword.toCharArray()
                ).build();
        return new SSLConnectionSocketFactory(sslcontext, new NoopHostnameVerifier());
    }

    @Bean
    public HttpClient httpClient(SSLConnectionSocketFactory sslFactory) {
        return HttpClients.custom().setSSLSocketFactory(sslFactory).build();
    }

    @Bean
    public RestTemplate restTemplate(HttpClient httpClient) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        restTemplate.setErrorHandler(
                new DefaultResponseErrorHandler() {
                    @Override
                    protected boolean hasError(HttpStatus statusCode) {
                        return false;
                    }
                });

        return restTemplate;
    }

}
