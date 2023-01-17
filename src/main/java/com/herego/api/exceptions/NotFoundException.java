package com.herego.api.exceptions;

import javax.ws.rs.core.Response.Status;

import com.herego.api.dictionaries.ResponseEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends Exception {
    private int codeError;
    private String messageUser;
    private String messageSystem;
    private Status responsehttp;

    public NotFoundException(ResponseEnum response) {
        this.codeError = response.getCodeError();
        this.messageUser = response.getMessageUser();
        this.messageSystem = response.getMessageSystem();
        this.responsehttp = response.getResponsehttp();
    }

    public NotFoundException(ResponseEnum response, Exception e) {
        this.codeError = response.getCodeError();
        this.messageUser = response.getMessageUser();
        this.messageSystem = this.getClass().getCanonicalName() + e.getMessage();
        this.responsehttp = response.getResponsehttp();
    }
}
