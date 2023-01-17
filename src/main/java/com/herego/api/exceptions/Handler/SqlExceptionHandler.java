package com.herego.api.exceptions.Handler;

import java.sql.SQLException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.herego.api.dictionaries.ResponseEnum;
import com.herego.api.models.GenericResponse;

@Provider
public class SqlExceptionHandler implements ExceptionMapper<SQLException> {

    @Override
    public Response toResponse(SQLException e) {
        GenericResponse response = new GenericResponse(ResponseEnum.SQLERROR);
        response.setMessageSystem(e.getMessage());
        return Response.status(response.getHttpCode()).entity(response).build();
    }
}