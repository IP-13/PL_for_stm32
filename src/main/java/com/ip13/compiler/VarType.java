package com.ip13.compiler;

public enum VarType {
    BOOL("bool"),
    INT("int"),
    FLOAT("float"),
    STRING("string"),
    POINTER("pointer");

    public final String label;

    private VarType(String label) {
        this.label = label;
    }

    public static VarType getByString(String label) {
        return VarType.valueOf(label.toUpperCase());
    }

}
