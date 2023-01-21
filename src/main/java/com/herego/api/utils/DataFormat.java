package com.herego.api.utils;

import com.herego.api.dictionaries.StoreProcedureEnum;

public class DataFormat {
    public static String formaterSp(StoreProcedureEnum sp) {
        String caller = "call ";
        caller += sp.getSchema() + "." + sp.getName() + "(";
        for (int i = 1; i <= sp.getArguments(); i++) {
            caller += "?";
            if (i < sp.getArguments())
                caller += ",";
        }
        caller += ")";
        return caller;
    }

    public static String formaterFun(StoreProcedureEnum sp) {
        String caller = "Select * from ";
        caller += sp.getSchema() + "." + sp.getName() + "(";
        for (int i = 1; i <= sp.getArguments(); i++) {
            caller += "?";
            if (i < sp.getArguments())
                caller += ",";
        }
        caller += ")";
        return caller;
    }

}
