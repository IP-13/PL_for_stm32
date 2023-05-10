package com.ip13.compiler;

public class FuncInfo {
    private String name;
    private int start; // start addr in byte-code
    private Type funcType;

    public FuncInfo(String name, int start, Type funcType) {
        this.name = name;
        this.start = start;
        this.funcType = funcType;
    }

    public void show() {
        System.out.println("Name: " + name + " Start: " + start + " Func type: " + funcType.label + " Var list: ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public Type getFuncType() {
        return funcType;
    }

    public void setFuncType(Type funcType) {
        this.funcType = funcType;
    }
}
