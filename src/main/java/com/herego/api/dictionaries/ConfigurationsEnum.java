package com.herego.api.dictionaries;

import lombok.Getter;

@Getter
public class ConfigurationsEnum {
    public enum UserType {
        ANUNCIADOR,
        EMPRENDEDOR,
        EMPRESA,
        SMART
    }

    public enum UserState {
        ACTIVO,
        INACTIVO,
        BANEADO
    }
}
