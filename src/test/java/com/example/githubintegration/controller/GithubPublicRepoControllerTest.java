package com.example.githubintegration.controller;

import com.example.githubintegration.models.dto.GenericResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubPublicRepoControllerTest {


    @Autowired
    private WebTestClient webClient;

    @Test
    public void shouldReturn200() {
        webClient
                .get()
                .uri("/github/{username}/repos", "HusanJava")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    public void shouldReturn406() {
        GenericResponse response = webClient
                .get()
                .uri("/github/{username}/repos", "HusanJava")
                .accept(MediaType.APPLICATION_XML)
                .exchange()
                .expectStatus()
                .is4xxClientError()
                .expectBody(GenericResponse.class)
                .returnResult()
                .getResponseBody();
        assert response != null;
        Assertions.assertEquals(406, response.getStatus());
    }

}