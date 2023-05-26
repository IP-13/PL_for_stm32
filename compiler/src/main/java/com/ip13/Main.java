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
        String program = args[0];

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

