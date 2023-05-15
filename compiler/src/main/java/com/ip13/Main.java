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
                my_func2(A : int) : pointer
                START
                RETURN "qwerty";
                FINISH
                
                my_func3() : pointer
                START
                B : float;
                FINISH
                
                MAIN
                my_func2(2, 3, 4);
                
                A : float;
                sum(2, 2);
                """;

        ItmovaLexer lexer = new ItmovaLexer(CharStreams.fromString(program));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ItmovaParser parser = new ItmovaParser(tokens);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ItmovaBaseListener(), tree);

        SuperClass.showByteCode();
    }
}

