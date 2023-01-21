package com.herego.api.controllers.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import com.herego.api.utils.annotations.StateUser;
import com.herego.api.utils.annotations.TypeUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUser {
    @TypeUser
    private String userType;
    @Past
    private Date birthday;
    @Email(message = "El correo no tiene un formato correcto")
    private String email;
    @StateUser(message = "El estado no corresponde a lo esperado")
    private String userState;
}
