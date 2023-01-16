package com.herego.api.exceptions.Handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.herego.api.exceptions.ConnectionExceptions;

@Provider
public class ConnectionExceptionsHandler implements ExceptionMapper<ConnectionExceptions> {

    @Override
    public Response toResponse(ConnectionExceptions e) {
        return Response.status(e.getResponsehttp()).entity(e).build();
    }
}