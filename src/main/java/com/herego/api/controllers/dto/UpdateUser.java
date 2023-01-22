package com.herego.api.controllers.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUser {
    @NotNull
    private Integer userType;
    @Past
    private Date birthday;
    @Email(message = "El correo no tiene un formato correcto")
    private String email;
    @NotNull(message = "El estado no corresponde a lo esperado")
    private Integer userState;
}
