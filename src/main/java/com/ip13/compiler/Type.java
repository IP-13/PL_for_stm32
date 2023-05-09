package com.ip13.compiler;

public enum Type {
    BOOL("bool"),
    INT("int"),
    FLOAT("float"),
    STRING("string"),
    POINTER("pointer"),
    VOID("void");

    public final String label;

    private Type(String label) {
        this.label = label;
    }

    public static Type strValue(String label) {
        return Type.valueOf(label.toUpperCase());
    }

}
