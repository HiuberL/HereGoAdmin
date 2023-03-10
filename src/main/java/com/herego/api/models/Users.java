package com.herego.api.models;

import java.math.BigInteger;
import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.herego.api.dictionaries.ConfigurationsEnum.UserType;
import com.herego.api.utils.annotations.DNI;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Users {
    private BigInteger id;
    @Pattern(regexp = "/^[a-zA-Z]+$/", message = "En el nombre solo pueden ir letras")
    @Size(max = 50, min = 3, message = "Debe ingresar sus nombres")
    private String name;
    @Pattern(regexp = "/^[a-zA-Z]+$/", message = "En el apellido solo pueden ir letras")
    @Size(max = 50, min = 3, message = "Debe ingresar sus apellido")
    private String lastName;
    @com.herego.api.utils.annotations.UserType
    private UserType userType;
    @Past
    private Date BirthDay;
    @Pattern(regexp = "/^[0-9]+$/", message = "El número celular no es válido")
    @Size(max = 10, min = 10, message = "El número celular debe ser de 10 dígitos")
    private String phone;
    @Pattern(regexp = "/^[0-9]+$/", message = "El número cédula no es válido")
    @Size(max = 10, min = 10, message = "El número cédula debe ser de 10 dígitos")
    @DNI
    private String dni;
    @Email(message = "El correo no tiene un formato correcto")
    private String email;
}
