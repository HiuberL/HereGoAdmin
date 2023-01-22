package com.herego.api.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Community {
    private Integer id;
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "En el nombre solo pueden ir letras")
    @Size(max = 50, min = 3, message = "Debe ingresar sus nombres")
    private String name;
    @Size(max = 200, min = 5, message = "Debe ingresar sus nombres")
    private String description;
    private Integer numberUsers;
    @NotNull(message = "El tipo de la comunidad es necesario")
    private Integer type;
    private Integer state;
}
