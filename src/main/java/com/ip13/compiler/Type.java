package com.ip13.compiler;

public enum Type {
    BOOL("bool"),
    INT("int"),
    FLOAT("float"),
    STRING("string"),
    POINTER("pointer"),
    VOID("void");

    private final String label;


    Type(String label) {
        this.label = label;
    }


    public String getLabel() {
        return label;
    }


    public static Type strValue(String label) {
        return Type.valueOf(label.toUpperCase());
    }


    public String getNumberFormat() {
        switch (this) {
            case BOOL -> {
                return "200";
            }
            case INT -> {
                return "201";
            }
            case FLOAT -> {
                return "202";
            }
            case STRING -> {
                return "203";
            }
            case POINTER -> {
                return "204";
            }
            case VOID -> {
                return "205";
            }
            default -> {
                return "ERROR";
            }
        }
    }
}