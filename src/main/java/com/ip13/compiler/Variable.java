package com.ip13.compiler;

public class Variable {
    private VarType varType;
    private String value;

    public Variable(VarType varType, String value) {
        this.varType = varType;
        this.value = value;
    }

    public VarType getVarType() {
        return varType;
    }

    public void setVarType(VarType varType) {
        this.varType = varType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
