package com.github.tsatam.webservicesexamples.apitest;

import com.github.tsatam.webservicesexamples.apitest.testclient.TestClient;
import com.github.tsatam.webservicesexamples.apitest.testclient.TestClientExtension;
import com.github.tsatam.webservicesexamples.apitest.timing.TimingExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ExtendWith({TestClientExtension.class, TimingExtension.class})
public class HealthTest {
    private TestClient client;

    public HealthTest(TestClient client) {
        this.client = client;
    }

    @Test
    public void testHealth() {
        var request = HttpRequest
                .newBuilder(URI.create("http://localhost:8080/health"))
                .GET()
                .build();

        client.send(request, HttpResponse.BodyHandlers.discarding())
            .andExpectStatus(HttpURLConnection.HTTP_OK);
    }
}
