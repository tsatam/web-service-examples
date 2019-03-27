package com.github.tsatam.webservicesexamples.apitest.testclient;

import java.net.http.HttpResponse;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertableHttpResponse<T> {
    private HttpResponse<T> innerResponse;

    public AssertableHttpResponse(HttpResponse<T> innerResponse) {
        this.innerResponse = innerResponse;
    }

    public AssertableHttpResponse<T> andExpectStatus(int status) {
        assertEquals(status, innerResponse.statusCode(),
                () -> format("Expected status <%d> but got <%d>", status, innerResponse.statusCode())
        );
        return this;
    }
}
