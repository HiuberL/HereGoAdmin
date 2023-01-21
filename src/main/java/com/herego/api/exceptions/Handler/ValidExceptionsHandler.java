package com.herego.api.exceptions.Handler;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.models.ValidErrorResponse;

@Provider
public class ValidExceptionsHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        ValidErrorResponse messageError = new ValidErrorResponse();
        messageError.setHttpCode(ResponseEnum.NOPASSVALID.getResponsehttp());
        messageError.setMessageUser("Existe problemas en su solicitud");
        messageError.setCodeError(ResponseEnum.NOPASSVALID.getCodeError());
        messageError.setMessageSystem(ResponseEnum.NOPASSVALID.getMessageSystem());
        e.getConstraintViolations().iterator().forEachRemaining(s -> {
            messageError.getReasons().add(s.getMessageTemplate());
        });
        return Response.status(messageError.getHttpCode()).entity(messageError).build();
    }
}