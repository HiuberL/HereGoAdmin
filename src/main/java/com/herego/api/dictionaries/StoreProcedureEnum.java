package com.herego.api.dictionaries;

import lombok.Getter;

@Getter
public enum StoreProcedureEnum {
    S_ALL_USER("public", "s_all_users_account", 3),
    S_USER_ID("public", "s_userbyid_account", 1),
    S_USER_BY_COMMUNITY("public", "s_users_by_community_account", 4),
    I_USER("public", "i_user_account", 10),
    U_USER_ID("public", "u_user_account", 6),
    U_USER_STATE_ID("public", "act_user_state_account", 4),
    S_ALL_COMMUNITY("public", "s_all_community_account", 3),
    S_COMMUNITY_BY_USER("public", "s_community_by_users_account", 4),
    S_COMMUNITY_BY_ID("public", "s_communitybyid_account", 1),
    D_COMMUNITY_USERS_ID("public", "d_community_users_account", 4),
    I_COMMUNITY_ACCOUNT("public", "i_community_account", 6),
    I_COMMUNITY_USER_ACCOUNT("public", "i_community_users_account", 5),
    U_COMMUNITY_USER_ACCOUNT("public", "u_community_users_account", 5);

    private String name;
    private String schema;
    private Integer arguments;

    StoreProcedureEnum(String schema, String name, Integer arguments) {
        this.name = name;
        this.schema = schema;
        this.arguments = arguments;
    }
}
