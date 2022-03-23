package com.github.rgulyamov.dreamtrip.app.rest.service.base;

import javax.ws.rs.core.Response;

/**
 * Base class for all rest services
 *
 * @author Guliamov Rustam
 */
public class BaseResource {
    /**
     * shared response that should be returned if request operation returns no data
     */
    protected final Response NOT_FOUND;

    /**
     * shared response that should be returned if client request invalid or unsupported format
     */
    protected final Response BAD_REQUEST;

    public BaseResource() {
        NOT_FOUND  = Response.status(Response.Status.NOT_FOUND).build();

        BAD_REQUEST = Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * return operation result as Response object
     *
     * @param result
     * @return
     */
    protected Response ok(Object result) {
        return Response.ok(result).build();
    }
}
