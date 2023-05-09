package com.ip13.compiler;

import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;

public class FuncInfo {
    private String name;
    private int start; // start addr in byte-code
    private Type funcType;
    private List<Pair<String, Type>> varList; // var_name, var_type

    public FuncInfo() {
        this.varList = new ArrayList<>();
    }

    public FuncInfo(String name, String funcType) {
        this.name = name;
        this.funcType = Type.strValue(funcType);
        this.varList = new ArrayList<>();
    }

    public void addParam(String paramName, String paramType) {
        varList.add(new Pair<>(paramName, Type.strValue(paramType)));
    }

    public void show() {
        System.out.print("Name: " + name +
                " Start: " + start +
                " Num of args: " + varList.size() +
                " Func type: " + funcType.label + " Var list: ");
        varList.forEach(var -> System.out.print("var name: " + var.a + " var type: " + var.b.label));
        System.out.println();
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
