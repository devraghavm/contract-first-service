package org.guardiandev.helloservice;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldIntegrationTest {

    @Test
    public void shouldReturnHelloWorld() {
        // Arrange

        // Act
        var result = makeGetRequest("http://localhost:4000/hello");

        // Assert
        assertThat(result).contains("Hello World!");
    }

    private String makeGetRequest(String uri) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        try {
            return client
                    .send(request, HttpResponse.BodyHandlers.ofString())
                    .body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}