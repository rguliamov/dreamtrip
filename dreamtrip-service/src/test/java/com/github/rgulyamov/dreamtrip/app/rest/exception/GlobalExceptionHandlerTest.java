package com.github.rgulyamov.dreamtrip.app.rest.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    private static ExceptionMapper<Exception> handler;

    @BeforeAll
    static void setup() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void testToResponseReturnServerError() {
        Exception exception = new Exception("exception");
        Response response = handler.toResponse(exception);
        assertEquals(response.getStatus(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }
}