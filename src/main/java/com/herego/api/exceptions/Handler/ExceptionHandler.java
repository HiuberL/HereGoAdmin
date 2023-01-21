package com.herego.api.exceptions.Handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.models.GenericResponse;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        GenericResponse response = new GenericResponse();
        response.initValues(ResponseEnum.NOTEXPECT);
        response.setMessageSystem(e.getMessage());
        return Response.status(response.getHttpCode()).entity(response).build();
    }
}
