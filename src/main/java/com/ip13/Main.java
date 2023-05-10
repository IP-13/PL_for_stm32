package com.ip13;

import com.ip13.antlr.ItmovaBaseListener;
import com.ip13.antlr.ItmovaLexer;
import com.ip13.antlr.ItmovaParser;
import com.ip13.compiler.SuperClass;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
        String program = """
                my_func(A: int) : int   
                START
                B : string;
                C : int;
                RETURN A;
                FINISH
                                        
                my_func2(A : int) : int
                START
                RETURN 2;
                FINISH
                                        
                my_func3(A : int) : int
                START
                                        
                RETURN sum(A, 2);
                                        
                                        
                                        
                FINISH
                                        
                                        
                MAIN
                A : float;
                B : string;
                C : int;
                print(A);
                sum(A, 10);
                """;

        ItmovaLexer lexer = new ItmovaLexer(CharStreams.fromString(program));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ItmovaParser parser = new ItmovaParser(tokens);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ItmovaBaseListener(), tree);


        System.out.println("_______________________________________________");
        System.out.println("Var map: ");
        SuperClass.showVarMap();
        System.out.println("_______________________________________________");
        System.out.println("Func list: ");
        SuperClass.showFuncList();
        System.out.println("_______________________________________________");
        System.out.println("Byte code: ");
        SuperClass.showByteCode();
    }
}

