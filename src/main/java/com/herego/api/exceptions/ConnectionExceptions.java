package com.herego.api.exceptions;

import java.sql.SQLException;

import javax.ws.rs.core.Response.Status;

import com.herego.api.dictionaries.ResponseEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectionExceptions extends SQLException {
    private int codeError;
    private String messageUser;
    private String messageSystem;
    private Status responsehttp;

    public ConnectionExceptions(ResponseEnum response) {
        this.codeError = response.getCodeError();
        this.messageUser = response.getMessageUser();
        this.messageSystem = response.getMessageSystem();
        this.responsehttp = response.getResponsehttp();
    }

    public ConnectionExceptions(ResponseEnum response, Exception e) {
        this.codeError = response.getCodeError();
        this.messageUser = response.getMessageUser();
        this.messageSystem = this.getClass().getCanonicalName() + e.getMessage();
        this.responsehttp = response.getResponsehttp();
    }
}
