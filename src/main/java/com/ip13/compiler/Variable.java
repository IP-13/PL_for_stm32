package com.ip13.compiler;

public class Variable {
    private Type type;
    private String value;

    public Variable(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getVarType() {
        return type;
    }

    public void setVarType(Type type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
