package com.herego.api.exceptions.Handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.exceptions.ConnectionException;
import com.herego.api.models.GenericResponse;

@Provider
public class ConnectionExceptionHandler implements ExceptionMapper<ConnectionException> {

    @Override
    public Response toResponse(ConnectionException e) {
        GenericResponse response = new GenericResponse();
        response.initValues(ResponseEnum.NOTCONNECTION);
        response.setMessageSystem(e.getMessageSystem());
        return Response.status(response.getHttpCode()).entity(response).build();
    }
}