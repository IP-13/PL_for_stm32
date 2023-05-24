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
                MAIN
                print(e());
                print(" ");
                print(pi());
                """;

        plClabLexer lexer = new plClabLexer(CharStreams.fromString(program));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        plClabParser parser = new plClabParser(tokens);
        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new plClabBaseListener(), tree);

        SuperClass.showByteCode(true);
        System.out.println("______________________________________");
        SuperClass.showByteCodeInNumberFormat(false);
    }
}

