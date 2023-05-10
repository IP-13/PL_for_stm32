package com.ip13.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperClass {
    private static final List<String> byteCode = new ArrayList<>();
    private static final List<FuncInfo> funcList = new ArrayList<>();
    private static Map<String, VarInfo> varMap = new HashMap();
    private static int entryPoint = 0; //

    public static void showVarMap() {
        varMap.forEach((name, varInfo) -> System.out.println("Name : " + name + " Type : " + varInfo.getType() + " Index: " + varInfo.getIndex()));
    }


    public static void showFuncList() {
        funcList.forEach(func -> {
            func.show();
            System.out.println();
        });
    }


    public static int getEntryPoint() {
        return entryPoint;
    }


    public static void showByteCode() {
        byteCode.forEach(System.out::println);
    }


    // antlr rules

    public static void program() {

    }


    public static void statements() {

    }


    public static void statement() {

    }


    public static void entryPoint(int line) {
        varMap = new HashMap<>(); // new scope of global vars starts with program entry point


        System.out.println("Program start at line: " + line);
        entryPoint = line;
    }


    public static void fromCycle() {

    }


    public static void lowerBorder() {

    }


    public static void upperBorder() {

    }


    public static void step() {

    }


    public static void ifOperator() {

    }


    public static void boolExpr() {

    }


    public static void funcDef(String name, String type) {
        varMap = new HashMap<>(); // new scope starts with new func def
        funcList.add(new FuncInfo(name, byteCode.size(), Type.strValue(type)));

    }


    public static void returnValueFuncCall() {
        byteCode.add("Return");
        byteCode.add("Value returns by func call");
    }


    public static void returnValueVariable(String varName) {
        byteCode.add("Return");
        byteCode.add("Value returns by variable");
        byteCode.add("Name: " + varName + " Index: " + String.valueOf(varMap.get(varName).getIndex()));
    }


    public static void returnValueLiteral() {
        byteCode.add(byteCode.size() - 1, "Return");
        byteCode.add(byteCode.size() - 1, "Value returns by literal");

    }


    public static void funcParams() {

    }


    public static void funcParam(String name, String type) {
        varMap.put(name, new VarInfo(varMap.size(), Type.strValue(type)));
        byteCode.add("Func param " + varMap.size() + " name: " + name + " Type: " + type);
    }


    public static void funcCall(String funcName) {
        byteCode.add("Func is called");
        byteCode.add(funcName);
    }


    public static void funcArgs() {

    }


    public static void funcArgFuncCall() {
        byteCode.add("Arg is passed through func call");
    }


    public static void funcArgVariable(String name) {
        byteCode.add("Arg is passed through variable");
        byteCode.add("Name: " + name + " Index: " + String.valueOf(varMap.get(name).getIndex()));
    }


    public static void funcArgLiteral() {
        byteCode.add(byteCode.size() - 1, "Arg is passed through literal");
    }


    public static void varDef(String name, String type) {
        varMap.put(name, new VarInfo(varMap.size(), Type.strValue(type)));

        byteCode.add("Var is defined. Name: " + name + " Type: " + type + " Index: " + (varMap.size() - 1));
    }


    public static void literal(String literal, Type type, int line) {
        byteCode.add("Literal at line: " + line + " Type: " + type + " Value: " + literal);
//        switch (type) {
//            case BOOL -> {
//
//            }
//            case INT -> {
//
//            }
//            case FLOAT -> {
//
//            }
//            case STRING -> {
//
//            }
//            default -> {
//                System.out.println("Error with literal at line: " + line);
//            }
//        }
    }

}
