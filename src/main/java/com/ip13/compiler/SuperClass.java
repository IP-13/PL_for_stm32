package com.ip13.compiler;

import java.util.*;

public class SuperClass {
    private static final List<String> byteCode = new ArrayList<>();
    private static final List<FuncInfo> funcList = new ArrayList<>();
    private static Map<String, VarInfo> varMap = new HashMap();
    private static int entryPoint = 0; //
    private static Stack<Integer> fromCycleStack = new Stack<>();
    private static Stack<Integer> ifOperatorStack = new Stack<>();

    
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


//    public static void changeStringLitToNumberFormat() {
//        byteCodeInNumberFormat.stream().filter().forEach();
//    }


    // antlr rules


    public static void program() {
        byteCode.add(ByteCodeCommands.exit.toString());
    }


    public static void statements() {

    }


    public static void statement() {

    }


    public static void entryPoint(int line) {
        varMap = new HashMap<>(); // new scope of global vars starts with program entry point
        entryPoint = byteCode.size();
        byteCode.add("MAIN start at " + entryPoint);
    }


    public static void fromCycle() {
        int counterAddr = fromCycleStack.pop();
        int currAddr = byteCode.size(); // addr of first command after last fromCycle command
        byteCode.add(ByteCodeCommands.jdec.toString());
        byteCode.add(String.valueOf(counterAddr - (currAddr + 1))); // after adding jdec command curr addr increased by 1
    }


    public static void stepInt(String lit) {
        byteCode.add(ByteCodeCommands.fint.toString());
        byteCode.add(lit);
        byteCode.add("counter");
        fromCycleStack.add(byteCode.size() - 1);
    }


    public static void stepVar(String varName) {
        byteCode.add(ByteCodeCommands.fvar.toString());
        byteCode.add(String.valueOf(varMap.get(varName).getIndex()));
        byteCode.add("counter");
        fromCycleStack.add(byteCode.size() - 1);
    }

    public static void upperBorderInt(String lit) {
        byteCode.add(ByteCodeCommands.fint.toString());
        byteCode.add(lit);
    }


    public static void upperBorderVar(String varName) {
        byteCode.add(ByteCodeCommands.fvar.toString());
        byteCode.add(String.valueOf(varMap.get(varName).getIndex()));
    }


    public static void lowerBorderInt(String lit) {
        byteCode.add(ByteCodeCommands.loop.toString());
        byteCode.add(ByteCodeCommands.fint.toString());
        byteCode.add(lit);
    }


    public static void lowerBorderVar(String varName) {
        byteCode.add(ByteCodeCommands.loop.toString());
        byteCode.add(ByteCodeCommands.fvar.toString());
        byteCode.add(String.valueOf(varMap.get(varName).getIndex()));
    }


    public static void ifOperator() {
        int jtAddr = ifOperatorStack.pop();
        int currAddr = byteCode.size();
        String addrToJt = String.valueOf(currAddr - jtAddr);
        byteCode.set(jtAddr, addrToJt);
    }


    public static void boolExpr() {
        byteCode.add(ByteCodeCommands.jt.toString());
        byteCode.add("stub for address to jump if condition is false");
        ifOperatorStack.add(byteCode.size() - 1);
    }


    public static void funcDef(String name, String type) {
        FuncInfo lastFunc = funcList.get(funcList.size() - 1);
        lastFunc.setName(name);
        lastFunc.setType(Type.strValue(type));
        byteCode.add(lastFunc.getStart(), String.valueOf(lastFunc.getNumOfParams()));
        byteCode.add(lastFunc.getStart(), type);
    }


    public static void returnValueFuncCall() {
        byteCode.add(ByteCodeCommands.rofc.toString());
    }


    public static void returnValueVariable(String varName) {
        byteCode.add(ByteCodeCommands.rvar.toString());
        byteCode.add(String.valueOf(varMap.get(varName).getIndex()));
    }


    public static void returnValueLiteral() {
        byteCode.add(byteCode.size() - 2, ByteCodeCommands.rlit.toString());
    }


    public static void funcParams() {
        varMap = new HashMap<>(); // new scope starts with new func def
        funcList.add(new FuncInfo(null, byteCode.size(), null, 0));
    }


    public static void funcParam(String name, String type) {
        if (funcList.size() == 0 || funcList.get(funcList.size() - 1).getName() != null) { // second condition means that previous function reached top rule: func def
            varMap = new HashMap<>(); // new scope starts with new func def
            funcList.add(new FuncInfo(null, byteCode.size(), null, 0));
        }

        varMap.put(name, new VarInfo(varMap.size(), Type.strValue(type)));
        funcList.get(funcList.size() - 1).incNumOfParams();
        byteCode.add(Type.strValue(type).getLabel());
    }


    public static void funcCall(String funcName) {
        byteCode.add(ByteCodeCommands.call.toString());
        byteCode.add(String.valueOf(funcList.stream().findFirst().orElse(null).getStart()));
    }


    public static void funcArgFuncCall() {
        byteCode.add(ByteCodeCommands.ofc.toString());
    }


    public static void funcArgVariable(String name) {
        byteCode.add(ByteCodeCommands.var.toString());
        byteCode.add(String.valueOf(varMap.get(name).getIndex()));
    }


    public static void funcArgLiteral() {
        byteCode.add(byteCode.size() - 2, ByteCodeCommands.lit.toString());
    }


    public static void varDef(String name, String type) {
        varMap.put(name, new VarInfo(varMap.size(), Type.strValue(type)));
        byteCode.add(type);
    }


    public static void literal(String literal, Type type, int line) {
        byteCode.add(type.getLabel());
        byteCode.add(literal);
    }

}