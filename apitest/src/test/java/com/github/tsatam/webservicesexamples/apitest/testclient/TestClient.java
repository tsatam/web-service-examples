package com.github.tsatam.webservicesexamples.apitest.testclient;

import org.opentest4j.AssertionFailedError;

import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.fail;

public final class TestClient {
    private final HttpClient client;

    public TestClient() {
        this(HttpClient.newHttpClient());
    }

    public TestClient(HttpClient client) {
        this.client = client;
    }

    public <T> AssertableHttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> bodyHandler) {
        try {
            return new AssertableHttpResponse<>(client.send(request, bodyHandler));
        } catch (ConnectException e) {
            throw new AssertionFailedError("Connection refused. Maybe the service is not running? ");
        } catch (InterruptedException | IOException e) {
            throw new AssertionFailedError("Exception thrown when attempting HTTP call", e);
        }
    }
}
