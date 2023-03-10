package com.herego.api.dictionaries;

import lombok.Getter;

@Getter
public enum StoreProcedureEnum {
    C_USER_ID("public", "c_userbyid_account", 5);

    private String name;
    private String schema;
    private Integer arguments;

    StoreProcedureEnum(String schema, String name, Integer arguments) {
        this.name = name;
        this.schema = schema;
        this.arguments = arguments;
    }
}
