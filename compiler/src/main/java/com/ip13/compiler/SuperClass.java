package com.ip13.compiler;

import com.ip13.Exceptions.UnknownFuncCallException;
import com.ip13.Exceptions.UnknownTypeException;

import java.util.*;

import static java.util.Objects.isNull;

public class SuperClass {
    private static final List<String> byteCode = new ArrayList<>();
    private static final List<FuncInfo> funcList = new ArrayList<>();
    private static Map<String, VarInfo> varMap = new HashMap<>();
    private static int entryPoint = 0; //
    private static final Stack<Integer> fromCycleStack = new Stack<>();
    private static final Stack<Integer> ifOperatorStack = new Stack<>();


    public static void showVarMap() {
        varMap.forEach((name, varInfo) -> System.out.println("Name : " + name + " Type : " + varInfo.getType() + " Index: " + varInfo.getIndex()));
    }


    public static void showFuncList() {
        funcList.forEach(func -> {
            func.show();
            System.out.println();
        });
    }


    public static void optimizeStringLiterals() {
        int currOverHead = 0;
    }
    public static int getEntryPoint() {
        return entryPoint;
    }


    public static void showByteCode() {
        byteCode.forEach(System.out::println);
    }


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


    public static void funcDef(String name, String type, int line) {
        FuncInfo lastFunc = funcList.get(funcList.size() - 1);
        lastFunc.setName(name);
        lastFunc.setType(defineType(type, "Unknown return type in func " + name + " definition at line " + line));
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


    public static void funcParam(String name, String type, int line) {
        if (funcList.size() == 0 || funcList.get(funcList.size() - 1).getName() != null) { // second condition means that previous function reached top rule: func def
            varMap = new HashMap<>(); // new scope starts with new func def
            funcList.add(new FuncInfo(null, byteCode.size(), null, 0));
        }

        ByteCodeCommands byteCodeType = defineType(type, "Unknown type " + type + " of func param " + name + " at line " + line);

        varMap.put(name, new VarInfo(varMap.size(), byteCodeType));
        funcList.get(funcList.size() - 1).incNumOfParams();
        byteCode.add(byteCodeType.toString());
    }


    public static void funcCall(String funcName, int line) {
        String itmovaFunc = ByteCodeCommands.getItmovaCore(funcName);

        if (!isNull(itmovaFunc)) {
            byteCode.add(itmovaFunc);
        } else {
            try {
                int userDefinedFuncStart = funcList.stream().
                        filter(funcInfo -> funcInfo.getName().equals(funcName)).
                        findFirst().
                        orElseThrow(() -> new UnknownFuncCallException("Call of unknown func " + funcName + " at line " + line)).
                        getStart();
                byteCode.add(ByteCodeCommands.call.toString());
                byteCode.add(String.valueOf(userDefinedFuncStart));
            } catch (NullPointerException ex) {
                throw new UnknownFuncCallException("Call of unknown func " + funcName + " at line " + line);
            }
        }
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


    public static void varDef(String name, String type, int line) {
        ByteCodeCommands byteCodeType = defineType(type, "Definition of variable " + name + " with unknown type " + type + " at line " + line);
        varMap.put(name, new VarInfo(varMap.size(), byteCodeType));
        byteCode.add(type);
    }


    public static void literal(String literal, ByteCodeCommands type, int line) {
        byteCode.add(type.toString());
        byteCode.add(literal);
    }


    private static ByteCodeCommands defineType(String type, String message) {
        switch (type) {
            case "bool" -> {
                return ByteCodeCommands.BOOL;
            }
            case "int" -> {
                return ByteCodeCommands.INT;
            }
            case "float" -> {
                return ByteCodeCommands.FLT;
            }
            case "string" -> {
                return ByteCodeCommands.STR;
            }
            case "pointer" -> {
                return ByteCodeCommands.PTR;
            }
            case "void" -> {
                return ByteCodeCommands.VOID;
            }
            default -> throw new UnknownTypeException(message);
        }
    }
}