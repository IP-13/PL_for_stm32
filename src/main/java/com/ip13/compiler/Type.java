package com.ip13.compiler;

public enum Type {
    BLN("bool"),
    INT("int"),
    FLT("float"),
    STR("string"),
    PTR("pointer"),
    VOID("void");

    private final String numberFormat;

    Type(String numberFormat) {
        this.numberFormat = numberFormat;
    }

    public static Type strValue(String label) {
        return Type.valueOf(label.toUpperCase());
    }

    public String getNumberFormat() {
        return numberFormat;
    }
}
