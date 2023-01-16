package com.herego.api.models;

import javax.json.bind.annotation.JsonbTransient;

import lombok.Getter;
import lombok.Setter;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class GenericResponse {
    private Integer codeError;
    private String messageSystem;
    private String messageUser;
    @JsonbTransient
    private Status httpCode;
}
