package com.ip13.compiler;

import com.ip13.Exceptions.UnknownFuncCallException;
import com.ip13.Exceptions.UnknownTypeException;

import java.util.*;

import static java.util.Objects.isNull;

public class SuperClass {
    private static final List<String> byteCode = new ArrayList<>();
    private static final List<FuncInfo> funcList = new ArrayList<>();
    private static Map<String, VarInfo> varMap = new HashMap<>();
    private static final List<String> stringLiteralsStorage = new ArrayList<>();
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
        int byteCodeSize = byteCode.size();

        for (int i = 0; i < byteCodeSize; i++) {
            if (byteCode.get(i).equals(ByteCodeCommands.lit.toString()) ||
                    byteCode.get(i).equals(ByteCodeCommands.rlit.toString())) {                     // check for literal
                if (byteCode.get(i + 1).equals(ByteCodeCommands.STR.toString())) {                  // check if it's a string literal
                    String s = stringLiteralsStorage.get(Integer.parseInt(byteCode.get(i + 2))); // gets string literal
                    int len = s.length() - 2;                                                       // subtract 2 because string has quotes at both sides
                    byteCode.set(i + 2, String.valueOf(byteCode.size()));                         // sets addr of string start at the end of bytecode
                    byteCode.add(String.valueOf(len));                                            // adds string literal size
                    for (int j = 0; j < len; j++) {                                                 // adds string chars
                        byteCode.add(String.valueOf(s.charAt(j + 1)));
                    }
                }
            }
        }
    }


    public static List<Integer> translateByteCodeToNumberFormat() {
        List<Integer> byteCodeInNumberFormat = new ArrayList<>();
        byteCode.forEach(command -> {
            if (command.equals("counter")) {
                byteCodeInNumberFormat.add(0);
            } else {

                int numberFormat = ByteCodeCommands.getCommandInNumberFormat(command);

                if (numberFormat != -1) {
                    byteCodeInNumberFormat.add(numberFormat);
                } else {
                    try {
                        byteCodeInNumberFormat.add(Integer.parseInt(command));
                    } catch (NumberFormatException ex) {
                        try {
                            float f = Float.parseFloat(command);
                            int i = Float.floatToIntBits(f);
                            byteCodeInNumberFormat.add(i);
                        } catch (NumberFormatException ignored) {

                        }

                        command.chars().forEach(byteCodeInNumberFormat::add);
                    }
                }
            }
        });

        return byteCodeInNumberFormat;
    }


    public static int getEntryPoint() {
        return entryPoint;
    }


    public static void showByteCode(boolean showCommandsNumber) {
        if (showCommandsNumber) {
            for (int i = 0; i < byteCode.size(); i++) {
                System.out.println(i + ":\t" + byteCode.get(i));
            }
        } else {
            byteCode.forEach(System.out::println);
        }
    }


    public static void showByteCodeInNumberFormat(boolean showCommandsNumber) {
        List<Integer> byteCodeInNumberFormat = translateByteCodeToNumberFormat();

        if (showCommandsNumber) {
            for (int i = 0; i < byteCodeInNumberFormat.size(); i++) {
                System.out.println(i + ":\t" + byteCodeInNumberFormat.get(i));
            }
        } else {
            byteCodeInNumberFormat.forEach(System.out::println);
        }
    }


    // antlr rules
    public static void program() {
        byteCode.add(ByteCodeCommands.exit.toString());
        optimizeStringLiterals();
    }


    public static void statements() {

    }


    public static void statement() {

    }


    public static void entryPoint(int line) {
        varMap = new HashMap<>(); // new scope of global vars starts with program entry point
        entryPoint = byteCode.size();
        byteCode.add("MAIN");
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
        ByteCodeCommands byteCodeType = defineType(type, "Unknown return type in func " + name + " definition at line " + line);
        FuncInfo lastFunc = funcList.get(funcList.size() - 1);
        lastFunc.setName(name);
        lastFunc.setType(byteCodeType);
        byteCode.add(lastFunc.getStart(), String.valueOf(lastFunc.getNumOfParams()));
        byteCode.add(lastFunc.getStart(), byteCodeType.toString());
        byteCode.add(ByteCodeCommands.jret.toString());
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
        String coreFunc = ByteCodeCommands.getCore(funcName);

        if (!isNull(coreFunc)) {
            byteCode.add(coreFunc);
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
        byteCode.add(byteCodeType.toString());
    }


    public static void literal(String literal, ByteCodeCommands type, int line) {
        byteCode.add(type.toString());
        if (type.equals(ByteCodeCommands.STR)) {
            byteCode.add(String.valueOf(stringLiteralsStorage.size()));
            stringLiteralsStorage.add(literal);
        } else if (type.equals(ByteCodeCommands.BOOL)) {
            if (literal.equals("TRUE")) {
                byteCode.add("1");
            } else {
                byteCode.add("0");
            }
        } else {
            byteCode.add(literal);
        }
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