package com.ip13;

import com.ip13.compiler.SuperClass;

public class Main {
    public static void main(String[] args) throws Exception {
        String program = """
                MAIN
                print("asdfasdf");
                """;

        SuperClass.generateFileForCLabExecution(program);
    }
}

