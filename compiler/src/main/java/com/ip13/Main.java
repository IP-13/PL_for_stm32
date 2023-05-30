package com.ip13;

import com.ip13.compiler.SuperClass;

public class Main {
    public static void main(String[] args) throws Exception {
        String program = """
                fact(N: int): int
                START
                  inc(N);
                  I: int;
                  inc(I);
                  RES: int;
                  inc(RES);
                  FROM 1 TO N WITH 1:
                  START
                    assign(RES, mul(RES, I));
                  inc(I);
                  FINISH
                  RETURN RES;
                FINISH
                MAIN
                print(fact(10));
                """;

        SuperClass.generateFileForCLabExecution(program);
    }
}

