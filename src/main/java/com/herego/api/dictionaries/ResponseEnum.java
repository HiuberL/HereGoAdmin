package com.herego.api.dictionaries;

import javax.ws.rs.core.Response.Status;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    OK(0, "Respuesta Exitosa", "Respuesta Exitosa", Status.OK),
    NOTFOUND(100, "No se ha encontrado información relacionada.", "No se ha encontrado información relacionada.",
            Status.NOT_FOUND),
    NOTCONNECTION(500, "Problemas al conectarse con la base de datos.", "Problemas al conectarse con la base de datos.",
            Status.SERVICE_UNAVAILABLE),
    NOPASSVALID(100, "", "Los datos ingresados no pasaron la validación",
            Status.BAD_REQUEST);

    private int codeError;
    private String messageUser;
    private String messageSystem;
    private Status responsehttp;

    ResponseEnum(int codeError, String messageUser, String messageSystem, Status responsehttp) {
        this.codeError = codeError;
        this.messageUser = messageUser;
        this.messageSystem = messageSystem;
        this.responsehttp = responsehttp;
    }
}
