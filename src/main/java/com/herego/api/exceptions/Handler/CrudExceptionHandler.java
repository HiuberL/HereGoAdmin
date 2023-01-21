package com.herego.api.exceptions.Handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.exceptions.CrudException;
import com.herego.api.models.GenericResponse;

@Provider
public class CrudExceptionHandler implements ExceptionMapper<CrudException> {

    @Override
    public Response toResponse(CrudException e) {
        GenericResponse response = new GenericResponse(ResponseEnum.BADREQUEST);
        response.setMessageSystem(e.getMessageSystem());
        response.setMessageUser(e.getMessageUser());
        return Response.status(response.getHttpCode()).entity(response).build();
    }
}