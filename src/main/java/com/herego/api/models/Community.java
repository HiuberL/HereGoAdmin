package com.herego.api.models;

import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String description;
    private Integer numberUsers;

}
