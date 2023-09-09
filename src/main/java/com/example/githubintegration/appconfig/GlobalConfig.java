package com.example.githubintegration.appconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Configuration
public class GlobalConfig {
    @Value("${github.bearer-token}")
    private String token;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, Constants.GITHUB_ACCEPT_HEADER)
                .defaultHeader(Constants.GITHUB_VERSION_KEY, Constants.GITHUB_VERSION)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer" + token)
                .build();
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
                var defaultAttributes = super.getErrorAttributes(request, options);
                defaultAttributes.remove("path");
                defaultAttributes.remove("timestamp");
                Object error = defaultAttributes.remove("error");
                defaultAttributes.remove("requestId");
                defaultAttributes.put("Message", error);
                return defaultAttributes;

            }
        };
    }

}
