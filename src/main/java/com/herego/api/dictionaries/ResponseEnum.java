package com.herego.api.dictionaries;

import javax.ws.rs.core.Response.Status;

import lombok.Getter;

@Getter
public enum ResponseEnum {
        OK(0, "Respuesta Exitosa", "Respuesta Exitosa", Status.OK),
        NOTFOUND(100, "No se ha encontrado información relacionada.", "No se ha encontrado información relacionada.",
                        Status.NOT_FOUND),
        NOPASSVALID(101, "", "Los datos ingresados no pasaron la validación",
                        Status.BAD_REQUEST),
        NOTCONNECTION(500, "Problemas al conectarse con la base de datos.",
                        "Problemas al conectarse con la base de datos.",
                        Status.SERVICE_UNAVAILABLE),
        SQLERROR(501, "Esta opción no está disponible por el momento", "",
                        Status.SERVICE_UNAVAILABLE),
        NOTEXPECT(999, "Problemas en el servicio por el momento", "",
                        Status.SERVICE_UNAVAILABLE);

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
