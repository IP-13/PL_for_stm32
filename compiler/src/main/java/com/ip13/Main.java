package com.ip13;

import com.ip13.antlr.plClabBaseListener;
import com.ip13.antlr.plClabLexer;
import com.ip13.antlr.plClabParser;
import com.ip13.compiler.SuperClass;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class Main {
    public static void main(String[] args) throws Exception {
        String program = """
                fact(N:int):int
                START
                
                I:int;
                assign(I, 1);
                
                RES:int;
                assign(RES, 1);
                
                inc(N);
                
                FROM 1 TO N WITH 1:
                START
                
                assign(RES, mul(RES, I));
                inc(I);
                
                FINISH
                
                RETURN RES;
                FINISH

                MAIN
                A:int;
                assign(A, 5);
                print(fact(A));
                """;

        plClabLexer lexer = new plClabLexer(CharStreams.fromString(program));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        plClabParser parser = new plClabParser(tokens);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new plClabBaseListener(), tree);

        System.out.println("Byte-code size is: " + SuperClass.getByteCodeSize());
        System.out.println("______________________________________");
        System.out.println("Program starts at: " + SuperClass.getEntryPoint());
        System.out.println("______________________________________");
        SuperClass.showByteCode(true);
        System.out.println("______________________________________");
        SuperClass.showByteCodeInNumberFormat(false);
    }
}

