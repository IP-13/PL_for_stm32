package com.ip13.compiler;

import java.util.*;

public class SuperClass {
    private static final StringBuilder byteCode = new StringBuilder();
    private static final Map<String, Variable> varMap = new HashMap<>();
    private static final List<FuncInfo> funcList = new ArrayList<>();
    private static int entryPoint; //

    public static void showVarMap() {
        varMap.forEach((key, value) -> System.out.println("Name is: " + key + " Type is: " + value.getVarType() + " Value is: " + value.getValue()));
    }

    public static void showFuncList() {
        funcList.forEach(func -> {
            func.show();
            System.out.println();
        });
    }

    public static StringBuilder getByteCode() {
        return byteCode;
    }

    public static int getEntryPoint() {
        return entryPoint;
    }


    // antlr rules

    public static void literal(String literal) {
        System.out.println("Litaral value is: " + literal);
    }

    public static void varDef(String varName, String varType) {
        varMap.put(varName, new Variable(Type.strValue(varType), null));
    }

    public static void returnValue() {

    }

    public static void funcArg() {

    }

    public static void funcArgs() {

    }

    public static void funcCall() {

    }

    public static void funcParam(String name, String type) {
        funcList.add(new FuncInfo());
        funcList.get(funcList.size() - 1).addParam(name, type);
    }

    public static void funcParams() {

    }

    public static void funcDef(String funcName, String funcType) {
        funcList.get(funcList.size() - 1).setName(funcName);
        funcList.get(funcList.size() - 1).setFuncType(Type.strValue(funcType));
    }

    public static void boolExpr() {

    }

    public static void ifOperator() {

    }

    public static void step() {

    }

    public static void upperBorder() {

    }

    public static void lowerBorder() {

    }

    public static void fromCycle() {

    }

    public static void entryPoint(int line) {
        System.out.println("Program start at line: " + line);
        entryPoint = line;
    }

    public static void statement() {

    }

    public static void statements() {

    }

    public static void program() {

    }

}
