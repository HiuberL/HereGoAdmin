package com.herego.api.exceptions;

import javax.ws.rs.core.Response.Status;

import com.herego.api.dictionaries.ResponseEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CrudException extends Exception {
    private int codeError;
    private String messageUser;
    private String messageSystem;
    private Status responsehttp;

    public CrudException(ResponseEnum response) {
        this.codeError = response.getCodeError();
        this.messageUser = response.getMessageUser();
        this.messageSystem = response.getMessageSystem();
        this.responsehttp = response.getResponsehttp();
    }

    public CrudException(ResponseEnum response, Exception e) {
        this.codeError = response.getCodeError();
        this.messageUser = response.getMessageUser();
        this.messageSystem = this.getClass().getCanonicalName() + e.getMessage();
        this.responsehttp = response.getResponsehttp();
    }
}
