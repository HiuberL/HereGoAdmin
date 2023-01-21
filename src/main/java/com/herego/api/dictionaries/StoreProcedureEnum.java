package com.herego.api.dictionaries;

import lombok.Getter;

@Getter
public enum StoreProcedureEnum {
    C_USER_ID("public", "s_userbyid_account", 1);

    private String name;
    private String schema;
    private Integer arguments;

    StoreProcedureEnum(String schema, String name, Integer arguments) {
        this.name = name;
        this.schema = schema;
        this.arguments = arguments;
    }
}
