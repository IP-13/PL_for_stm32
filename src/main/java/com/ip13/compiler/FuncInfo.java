package com.ip13.compiler;

public class FuncInfo {
    private String name;
    private int start; // start addr in byte-code
    private Type type;
    private int numOfParams;


    public FuncInfo(String name, int start, Type type, int numOfParams) {
        this.name = name;
        this.start = start;
        this.type = type;
        this.numOfParams = numOfParams;
    }


    public void show() {
        System.out.println("Name: " + name + " Start: " + start + " Func type: " + type + " Var list: ");
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


    public Type getType() {
        return type;
    }


    public void setType(Type type) {
        this.type = type;
    }


    public int getNumOfParams() {
        return numOfParams;
    }


    public void setNumOfParams(int numOfParams) {
        this.numOfParams = numOfParams;
    }


    public void incNumOfParams() {
        numOfParams++;
    }
}
