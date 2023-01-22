package com.herego.api.dictionaries;

import lombok.Getter;

@Getter
public enum StoreProcedureEnum {
    S_ALL_USER("public", "s_all_users_account", 2),
    S_USER_ID("public", "s_userbyid_account", 1),
    I_USER("public", "i_user_account", 10),
    U_USER_ID("public", "u_user_account", 6),
    U_USER_STATE_ID("public", "act_user_state_account", 4);

    private String name;
    private String schema;
    private Integer arguments;

    StoreProcedureEnum(String schema, String name, Integer arguments) {
        this.name = name;
        this.schema = schema;
        this.arguments = arguments;
    }
}
