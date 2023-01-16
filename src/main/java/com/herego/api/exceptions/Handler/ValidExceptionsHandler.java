package com.herego.api.exceptions.Handler;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.models.GenericResponse;

@Provider
public class ValidExceptionsHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        GenericResponse messageError = new GenericResponse();
        messageError.setHttpCode(ResponseEnum.NOPASSVALID.getResponsehttp());
        messageError.setMessageUser(e.getMessage());
        messageError.setCodeError(ResponseEnum.NOPASSVALID.getCodeError());
        messageError.setMessageSystem(ResponseEnum.NOPASSVALID.getMessageSystem());
        return Response.status(messageError.getHttpCode()).entity(messageError).build();
    }
}