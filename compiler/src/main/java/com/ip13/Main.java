package com.ip13;

import com.ip13.antlr.ItmovaBaseListener;
import com.ip13.antlr.ItmovaLexer;
import com.ip13.antlr.ItmovaParser;
import com.ip13.compiler.SuperClass;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class Main {
    public static void main(String[] args) throws Exception {
        String program = """ 
                my_func(S1 : string, S2 : string) : int
                START
                FROM 1 TO 10 WITH 1:
                START
                print(10);
                FINISH
                RETURN "123";
                FINISH
                                
                my_func2() : void
                START
                F :float;
                FINISH
                MAIN
                IF less(2.33, 3):
                START
                S : string;
                print(my_func("qwerty", "123"));
                my_func2();
                FINISH
                """;

        ItmovaLexer lexer = new ItmovaLexer(CharStreams.fromString(program));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ItmovaParser parser = new ItmovaParser(tokens);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ItmovaBaseListener(), tree);

        SuperClass.showByteCode(true);
        System.out.println("______________________________________");
        SuperClass.showByteCodeInNumberFormat(true);

    }
}

