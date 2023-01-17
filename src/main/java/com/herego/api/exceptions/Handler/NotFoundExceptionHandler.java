package com.herego.api.exceptions.Handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.exceptions.NotFoundException;
import com.herego.api.models.GenericResponse;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        GenericResponse response = new GenericResponse();
        response.initValues(ResponseEnum.NOTFOUND);
        response.setMessageSystem(e.getMessageSystem());
        return Response.status(response.getHttpCode()).entity(response).build();
    }
}